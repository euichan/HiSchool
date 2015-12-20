"""hischool_server URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.8/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Add an import:  from blog import urls as blog_urls
    2. Add a URL to urlpatterns:  url(r'^blog/', include(blog_urls))
"""
from django.conf.urls import include, url
from django.contrib import admin
from django.contrib.auth import views
from hischool.views import index,signup, signin, get_profile_image,late_chk,late_manage, hw_write, hw_list,only_teacher, logout
from hischool import apiurls
from rest_framework.routers import DefaultRouter



#router = DefaultRouter()
#router.register(r'api/xy/',CLASSVIEW)

urlpatterns = [
    url(r'^$', index),
    url(r'^admin/', include(admin.site.urls)),
    url(r'^signin/$', signin),
    url(r'^signup/$', signup),
    url(r'^logout/$', logout),
    url(r'^late/manage/$', late_manage),
    url(r'^late/check/$', late_chk),
    url(r'^homework/write/$', only_teacher(hw_write)),
    url(r'^homework/$', hw_list),

    url(r'^logout/$', views.logout, name='logout'),
    url(r'^api/' , include(apiurls))
    
]
