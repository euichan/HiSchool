# -*- coding:utf-8 -*-
from django.test import TestCase
from django.core.urlresolvers import reverse
from rest_framework.status import *
from rest_framework.test import APITestCase, APIClient
from rest_framework.test import APIRequestFactory




# test order ref : http://stackoverflow.com/questions/2581005/django-testcase-testing-order


class BasicAccountTest(APITestCase):
    def SetUp(self):
        self.client = APIClient()

    def TestSignupTrue(self,name, phone, password, student, num, grade, ban):
        url = '/signup/'
        data = {
            "name":unicode(name),
            "phone":phone,
            "password":password,
            "grade":grade,
            "ban":ban,
            "num":num,
            "student":student,
        }
        res = self.client.post(url,data)
        self.assertEqual(res.status_code, HTTP_201_CREATED)

    def TestSigninTrue(self,phone,password):
        url = '/signin/'
        data = {
            "phone":phone,
            "password":password,
            
        }
        res = self.client.post(url,data)

        self.assertEqual(self.client.session['phone'],phone)
        self.assertEqual(res.status_code, HTTP_200_OK)


class AccountTests(BasicAccountTest):

    def TestSignupDuplicate(self,name, phone, password,student, num, grade, ban):
        """ duplicate signup check """
        url = '/signup/'
        data = {
            "name":unicode(name),
            "phone":phone,
            "password":password,
            "grade":grade,
            "ban":ban,
            "num":num,
            "student":student,
        }
        res = self.client.post(url,data)
        self.assertEqual(res.status_code, HTTP_409_CONFLICT)



    def TestSigninFalse(self,phone,password):
        url = '/signin/'
        data = {
            "phone":phone,
            "password":password,
        }
        res = self.client.post(url,data)
        self.assertEqual(res.status_code, HTTP_403_FORBIDDEN)
       
    
    def test_account(self):
        name = u"김믜믜"
        phone = "01011112222"
        student = "T"
        password = "1234"
        grade = "2"
        ban = "2"
        num = "8"
        self.TestSignupTrue(name, phone, password,student, num, grade, ban)
        self.TestSignupDuplicate(name, phone, password,student, num, grade, ban)
        self.TestSigninTrue(phone,password)
        self.TestSigninFalse(phone,password+"f")
    """
    def test_fb_signup(self):
        url = '/signup/'
        data = {
            "name":u"김믜믜",
            "phone":"01011112222",
            "fb_id":"100004921848344",
            "password":"1234",
            "grade":"2",
            "ban":"2",
            "num":"8",
        }
        res = self.client.post(url,data)
        self.assertEqual(res.status_code, HTTP_201_CREATED)

    """
    """
    def test_get_profile_image(self):
        url = '/api/get-profile-image/100004800463008/'
        data = {
            "url":"http://hischool.chmod777.kr/static/fp-img/100004800463008.jpg"
        }
        res = self.client.get(url)
        self.assertEqual(res.data, data)
        self.assertEqual(res.status_code, HTTP_200_OK)
    """

class TestTutorial(BasicAccountTest):
    pass
    #반장학번
    #등교시간


class XYTest(BasicAccountTest):
    def TestUpdateXY(self):
        #self.sign
        url = '/api/xy/'
        data = {

            "x":"126.9696625",
            "y":"37.5493083"
        }

        res = self.client.put(url,data,format='json')
        #self.assertEqual(res.status_code, HTTP_200_OK)

    def TestGetXY(self):
        pass

    def test_xy(self):
        self.TestUpdateXY()
        self.TestGetXY()



