from django.contrib.auth.models import Group
from rest_framework import serializers
from hischool.models import Student,Late,Notice, Class


class XYSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Student
        fields = ('x','y')


class LateSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Late
        fields = ('date','members')


class NoticeSerializer(serializers.ModelSerializer):
    class Meta:
        model = Notice
        fields = ('author','content')
