from django.http import HttpResponse

from hischool.models import Student,Notice,Late,Class,Teacher
from hischool.serializer import XYSerializer, LateSerializer, NoticeSerializer
from rest_framework import generics, viewsets
from rest_framework import mixins
from rest_framework.status import *

class XYCollection(mixins.ListModelMixin,
    mixins.CreateModelMixin,
    mixins.UpdateModelMixin,
    generics.GenericAPIView):
    serializer_class = XYSerializer
    
    def get_queryset(self):
        req = self.request
        print req.session.keys()
        try:
            if 'phone' not in req.session.keys() or req.session['is_teacher'] == 'T': 
        
                return HttpResponse(HTTP_403_FORBIDDEN)
        except:
            return HttpResponse(HTTP_403_FORBIDDEN)
        c = Student.objects.get(phone=phone).cls
        return c.members

    def get(self, request, *args, **kwargs):
        return self.list(request, *args, **kwargs)

    def put(self, request, *args, **kwargs):
        return self.update(request, *args, **kwargs)

    def post(self, request, *args, **kwargs):
        return self.create(request, *args, **kwargs)


class LateCollection(generics.ListCreateAPIView):
    serializer_class = LateSerializer
    queryset = Late.objects.all()
"""    def get_queryset(self):
        print self.request.session
        phone = self.request.session['phone']
        return cls.objects.all()
"""
class NoticeCollection(generics.ListCreateAPIView):
    serializer_class = NoticeSerializer
    def get_queryset(self):
        try:
            print self.request.session.keys(), "session"
            print self.request.COOKIES, "cookie"
            t = Teacher.objects.get(phone=self.request.session['phone'])
            print t.name
        except KeyError:
            return HttpResponse(HTTP_403_FORBIDDEN)

        return Notice.objects.filter(author=t).all()

def send_gcm(request,msg):
    def is_student(phone):
        try:
            s = Student.objects.get(phone=phone)
        except Student.DoesNotExist: 
            return None
        return s
    phone = request.session['phone']
    s = is_student(phone)

    if request.method == "GET":
        from gcm import GCM
        gcm = GCM("AIzaSyDxhSbm0PVsM9Y2eMk9Bpq3773Esazj9f0")
        data = {'message': msg}
        reg_ids = []
        
        if s != None:
            reg_ids.append(s.cls.teacher.gcm_id)
        else: # If Teacher, than broad case
            t = Teacher.objects.get(phone=phone)
            for s  in t.cls.members:
                reg_ids.append(s)
        response = gcm.json_request(registration_ids=reg_ids, data=data)
        if 'errors' in response:
            print response
            return HttpResponse(HTTP_500_INTERNAL_SERVER_ERROR)
        else:
            return HttpResponse(HTTP_200_OK)
    else:
        return HttpResponse(status=HTTP_400_BAD_REQUEST)

def message(request):
    pass
