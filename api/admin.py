from django.contrib import admin

from .models import *


class UserProfilesAdmin(admin.ModelAdmin):
    list_display = ('user', 'is_manager', 'is_vp', 'is_director', 'is_partnership_manager', 'is_partnership_staff')


admin.site.register(UserProfile)
admin.site.register(PKS)
admin.site.register(Mou_Nda)
admin.site.register(Attachment)
admin.site.register(Scope)
admin.site.register(Functionary)
admin.site.register(RAB)
