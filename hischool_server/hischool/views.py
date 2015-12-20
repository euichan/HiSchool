# -*- coding: utf-8 -*-
from django.shortcuts import render,redirect
from django.http import HttpResponse
from hischool_server.settings import STATIC_ROOT
from hischool.serializer import XYSerializer
from hischool.models import Student
from hischool.models import Class,Teacher
from django.contrib.auth import login

from rest_framework import generics
from rest_framework.status import *
from rest_framework.views import APIView
from rest_framework.authentication import BasicAuthentication, SessionAuthentication
from rest_framework.permissions import IsAuthenticated
from rest_framework.response import Response
from rest_framework import mixins
from django.views.decorators.csrf import csrf_exempt

"""

설명
=====
(sphinx)

class MyBasicAuthentication(BasicAuthentication):

    def authenticate(self, request):
        print request
        user, _ = super(MyBasicAuthentication, self).authenticate(request)
        login(request, user)
        return user, _


class ExampleView(APIView):
    authentication_classes = (SessionAuthentication, MyBasicAuthentication)
    permission_classes = (IsAuthenticated,)

    def get(self, request, format=None):
        content = {
            'user': unicode(request.user),
            'auth': unicode(request.auth),  # None
        }
        return Response(content)
"""

def login_required(f):
    def wrap(request,*args, **kwargs):
        if "phone" not in request.session.keys():
            return redirect("/signin/")
        return f(request,*args,**kwargs)
    wrap.__doc__ = f.__doc__
    wrap.__name__ = f.__name__
    return wrap

def only_teacher(f):
    def wrap(request,*args, **kwargs):
        if "teacher" not in request.session.keys():
            return redirect("/")
        return f(request,*args,**kwargs)
    wrap.__doc__ = f.__doc__
    wrap.__name__ = f.__name__
    return wrap

def index(request):
    return render(request,"teacher_index.html")

def late_chk(request):
    return render(request,"teacher_latecheck.html")

def late_manage(request):
    return render(request,"teacher_latemanage.html")

def hw_list(request):
    return render(request,"homework_view.html")

def hw_write(request):
    return render(request,"homework_upload.html")

def logout(request):
    del request.session['phone']
    return HttpResponse(HTTP_200_OK)

@csrf_exempt
def signin(request):
    if request.method == 'GET':
        #return HttpResponse(status=HTTP_400_BAD_REQUEST)
        return render(request,'signin.html')
    elif request.method == 'POST':    
        req = request.POST
        print req
        try:
            phone = req["phone"]
        except KeyError:
            return HttpResponse(status=HTTP_403_FORBIDDEN)
        try:
            user = Student.objects.get(phone=phone)
        except Student.DoesNotExist:
            try:
                user = Teacher.objects.get(phone=req["phone"])
                request.session['is_teachear'] = 'T'
            except Teacher.DoesNotExist:
                return HttpResponse(status=HTTP_403_FORBIDDEN)
                
        if user.check_password(req["password"]) == True:
            request.session['phone'] = req['phone']
            request.session['name'] = user.name
            return HttpResponse(status=HTTP_200_OK)

        
    else:
        return HttpResponse(status=HTTP_400_BAD_REQUEST)

@csrf_exempt
def signup(request):
    if request.method == 'GET':
        return render("signup.html")
    elif request.method == 'POST':
        req = request.POST
        phone = req['phone']
        print req
        if req['student'] == u'T':
            s,created = Student.objects.get_or_create(phone=phone)
            if created == True: # not exsist
                c, created = Class.objects.get_or_create(grade=req['grade'], ban=req['ban'])
                if created == True:
                    c.save()

                s.phone=phone
                s.gcm_id = req['gcm_id']
                s.name=req['name']
                s.num=req['num']
                s.fb_id=req.get('fb_id'),
                s.cls=c
                s.set_password(req['password'])
                s.save()
                return HttpResponse(status=HTTP_201_CREATED)
            else:
                return HttpResponse(status=HTTP_409_CONFLICT)

        else:
            teacher,created = Teacher.objects.get_or_create(phone=phone)
            if created == True: # not exsist
                c, created = Class.objects.get_or_create(grade=req['grade'], ban=req['ban'])
                if created == True:
                    c.save()

                teacher.phone=phone
                teacher.name=req['name']
                teacher.fb_id=req.get('fb_id'),
                teacher.cls=c
                teacher.set_password(req['password'])
                teacher.save()
                return HttpResponse(status=HTTP_201_CREATED)
            else:
                return HttpResponse(status=HTTP_409_CONFLICT)

    

def get_profile_image(request,phone):
    if request.method == 'GET':
        from urllib import urlopen
        from json import dumps
        import os
        res_data = {}
        filename = fb_id+".jpg"
        path = os.path.join(STATIC_ROOT,"img")

        f = open(path,"wb")
        f.write(urlopen("status=HTTP://graph.facebook.com/100004800463008/picture").read())
        f.close()
        
        current_domain = request.build_absolute_uri()
        print current_domain
        res_data['url'] = current_domain+"/static/pf-img/"+filename
        return Response(status=HTTPResponse(dumps(res_data)), content_type="application/json")
    else:
        return Response(status=HTTP_400_BAD_REQUEST)

def get_class_info(request):
    if request.method == 'POST':
        req = request.POST
        g = req['grade']
        b = req['ban']
        num = req['num']
        c = Class.objects.filter(grade=g,ban=b).first()
        student = Student.objects.filter(cls=c,num=num)
        c.class_president = student
        c.save()

    else:
        return HttpResponse(status=HTTP_400_BAD_REQUEST)

#def signup_for_demo(request):
