# Generated by Django 4.2.16 on 2024-10-03 07:26

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
    ]

    operations = [
        migrations.CreateModel(
            name='Functionary',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('full_name', models.CharField(max_length=100)),
                ('title', models.CharField(max_length=20)),
            ],
        ),
        migrations.CreateModel(
            name='Mou_Nda',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('base', models.CharField(choices=[('m', 'MoU'), ('n', 'NDA')], max_length=10)),
                ('submission_date', models.DateTimeField(auto_now_add=True)),
                ('partnership_title', models.CharField(max_length=255)),
                ('background', models.TextField()),
                ('note', models.TextField(blank=True, null=True)),
                ('partnership_candidate', models.CharField(max_length=255)),
                ('status', models.CharField(choices=[('p', 'pengajuan'), ('r', 'revisi'), ('t', 'ditolak'), ('m', 'manajer'), ('v', 'vp'), ('d', 'direksi'), ('mk', 'manager_kemitraan'), ('sk', 'staff_kemitraan')], max_length=20)),
                ('response_text', models.TextField()),
                ('approval_note', models.TextField()),
                ('mou_nda_number', models.CharField(max_length=50)),
                ('approval_completion_date', models.DateTimeField(blank=True, null=True)),
                ('user', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL)),
            ],
        ),
        migrations.CreateModel(
            name='PKS',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('partnership_type', models.CharField(choices=[('JO', 'Join Operation'), ('JI', 'Join Investment'), ('R', 'Reseller'), ('B', 'Bundling Layanan')], max_length=25)),
                ('submission_date', models.DateTimeField(auto_now_add=True)),
                ('budget_type', models.CharField(choices=[('o', 'Opex'), ('c', 'Capex'), ('oc', 'Opex & Capex')], max_length=10)),
                ('budget_number', models.CharField(max_length=100)),
                ('partnership_method', models.CharField(choices=[('t1', 'Tunjuk Langsung'), ('t2', 'Terbuka')], max_length=20)),
                ('material_type', models.CharField(choices=[('m', 'material_only'), ('s', 'service_only'), ('ms', 'material_and_service')], max_length=20)),
                ('title', models.CharField(max_length=255)),
                ('background', models.TextField()),
                ('note', models.TextField(blank=True, null=True)),
                ('partnership_candidate', models.CharField(max_length=255)),
                ('status', models.CharField(choices=[('p', 'pengajuan'), ('r', 'revisi'), ('t', 'ditolak'), ('m', 'manajer'), ('v', 'vp'), ('d', 'direksi'), ('mk', 'manager_kemitraan'), ('sk', 'staff_kemitraan')], max_length=20)),
                ('response_text', models.TextField()),
                ('approval_note', models.TextField()),
                ('mou_nda_number', models.CharField(max_length=50)),
                ('approval_completion_date', models.DateTimeField(blank=True, null=True)),
                ('user', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL)),
            ],
        ),
        migrations.CreateModel(
            name='UserProfile',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('is_manager', models.BooleanField(default=False)),
                ('is_vp', models.BooleanField(default=False)),
                ('is_director', models.BooleanField(default=False)),
                ('is_partnership_manager', models.BooleanField(default=False)),
                ('is_partnership_staff', models.BooleanField(default=False)),
                ('user', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL)),
            ],
        ),
        migrations.CreateModel(
            name='Scope',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('scope_name', models.CharField(max_length=150)),
                ('mou_nda', models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.CASCADE, to='api.mou_nda')),
                ('pks', models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.CASCADE, to='api.pks')),
            ],
        ),
        migrations.CreateModel(
            name='RAB',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('customer', models.CharField(max_length=255)),
                ('product', models.CharField(max_length=255)),
                ('is_pln', models.BooleanField(default=False)),
                ('revenue', models.BigIntegerField()),
                ('cost', models.BigIntegerField()),
                ('cost_desc', models.TextField()),
                ('mou_nda', models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.CASCADE, to='api.mou_nda')),
                ('pks', models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.CASCADE, to='api.pks')),
            ],
        ),
        migrations.CreateModel(
            name='Attachment',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('file', models.FileField(upload_to='media/attachment')),
                ('mou_nda', models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.CASCADE, to='api.mou_nda')),
                ('pks', models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.CASCADE, to='api.pks')),
            ],
        ),
    ]