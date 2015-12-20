from django.contrib import admin
from django.contrib.auth import get_user_model
from hischool.models import Student, Late, Class,Teacher, Notice

admin.site.register(Late)
admin.site.register(Class)
admin.site.register(Student)
admin.site.register(Teacher)
admin.site.register(Notice)
