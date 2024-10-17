import os
from os import path

from django.conf import settings
from django.contrib.auth.models import User
from django.core.exceptions import ObjectDoesNotExist
from django.core.validators import MaxValueValidator, MinValueValidator
from django.db import models

base_choices = [
    ('m', 'MoU'),
    ('n', "NDA")
]

type_choices = [
    ('JO', 'Join Operation'),
    ('JI', 'Join Investment'),
    ('R', 'Reseller'),
    ('B', 'Bundling Layanan')
]

status_choices = [
    ('p', 'pengajuan'),
    ('r', 'revisi'),
    ('t', 'ditolak'),
    ('m', 'manajer'),
    ('v', 'vp'),
    ('d1', 'direksi'),
    ('mk', 'manager_kemitraan'),
    ('sk', 'staff_kemitraan'),
    ('f', 'Selesai')
]

budget_type_choices = [
    ('o', 'Opex'),
    ('c', 'Capex'),
    ('oc', 'Opex & Capex'),
]

methode_type_choices = [
    ('t1', 'Tunjuk Langsung'),
    ('t2', 'Terbuka'),
]

material_type_choices = [
    ('m', 'material_only'),
    ('s', 'service_only'),
    ('ms', 'material_and_service'),
]


class UserProfile(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    is_manager = models.BooleanField(default=False)
    is_vp = models.BooleanField(default=False)
    is_director = models.BooleanField(default=False)
    is_partnership_manager = models.BooleanField(default=False)
    is_partnership_staff = models.BooleanField(default=False)

    def __str__(self):
        return self.user.username


class Functionary(models.Model):
    full_name = models.CharField(max_length=100)
    title = models.CharField(max_length=20)

    def __str__(self):
        return f"{self.full_name} {self.title}"


class Mou_Nda(models.Model):
    base = models.CharField(choices=base_choices, max_length=10)
    submission_date = models.DateTimeField(auto_now_add=True)
    partnership_title = models.CharField(max_length=255)
    background = models.TextField()
    note = models.TextField(null=True, blank=True)
    partnership_candidate = models.CharField(max_length=255)
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    status = models.CharField(choices=status_choices, max_length=20, default='d')
    position_level = models.IntegerField(max_length=1, validators=[
        MaxValueValidator(7),
        MinValueValidator(1)
    ], default=1)
    is_stop_clock = models.BooleanField(default=False)
    response_text = models.TextField(null=True, blank=True)
    approval_note = models.TextField(null=True, blank=True)
    mou_nda_number = models.CharField(max_length=50, null=True, blank=True)
    approval_completion_date = models.DateTimeField(null=True, blank=True)
    official_undersign = models.ForeignKey(Functionary, on_delete=models.CASCADE, null=True, blank=True)

    def __str__(self):
        return f"permintaan {self.get_base_display()}, dengan id : {self.id}"


class PKS(models.Model):
    partnership_type = models.CharField(choices=type_choices, max_length=25)
    submission_date = models.DateTimeField(auto_now_add=True)
    budget_type = models.CharField(max_length=10, choices=budget_type_choices)
    budget_number = models.CharField(max_length=100)
    partnership_method = models.CharField(max_length=20, choices=methode_type_choices)
    material_type = models.CharField(max_length=20, choices=material_type_choices)
    title = models.CharField(max_length=255)
    background = models.TextField()
    note = models.TextField(null=True, blank=True)
    partnership_candidate = models.CharField(max_length=255)
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    status = models.CharField(choices=status_choices, max_length=20, default='d')
    position_level = models.IntegerField(max_length=1, validators=[
        MaxValueValidator(7),
        MinValueValidator(1)
    ], default=1)
    is_stop_clock = models.BooleanField(default=False)
    response_text = models.TextField(null=True, blank=True)
    approval_note = models.TextField(null=True, blank=True)
    pks_number = models.CharField(max_length=50, null=True, blank=True)
    approval_completion_date = models.DateTimeField(null=True, blank=True)
    official_undersign = models.ForeignKey(Functionary, on_delete=models.CASCADE, null=True, blank=True)

    def __str__(self):
        return f"permintaan PKS dengan id : {self.id}"


class Attachment(models.Model):
    file = models.FileField(upload_to='attachment')
    mou_nda = models.ForeignKey(Mou_Nda, on_delete=models.CASCADE, null=True, blank=True,
                                related_name='attachments_mou', related_query_name='attachments_mou')
    pks = models.ForeignKey(PKS, on_delete=models.CASCADE, null=True, blank=True,
                            related_name='attachments_pks', related_query_name='attachments_pks')

    def delete(self, using=None, *args, **kwargs):
        try:
            file_path = path.join(settings.MEDIA_ROOT, self.file.name)
            if path.isfile(file_path):
                os.remove(file_path)
        except ObjectDoesNotExist as e:
            print(e)

    def __str__(self):
        return self.file.name.split('/')[-1]

    def get_file_url(self):
        return self.file.url


class Scope(models.Model):
    scope_name = models.CharField(max_length=150)
    mou_nda = models.ForeignKey(Mou_Nda, on_delete=models.CASCADE, null=True, blank=True,
                                related_name='scopes_mou', related_query_name='scopes_mou')
    pks = models.ForeignKey(PKS, on_delete=models.CASCADE, null=True, blank=True,
                            related_name='scopes_pks', related_query_name='scopes_pks')

    def __str__(self):
        return self.scope_name


class RAB(models.Model):
    customer = models.CharField(max_length=255)
    product = models.CharField(max_length=255)
    is_pln = models.BooleanField(default=False)
    revenue = models.BigIntegerField()
    cost = models.BigIntegerField()
    cost_desc = models.TextField()
    pks = models.ForeignKey(PKS, on_delete=models.CASCADE, null=True, blank=True,
                            related_name='rab', related_query_name='rab')
