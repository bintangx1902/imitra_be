# Generated by Django 4.2.16 on 2024-10-07 08:07

import django.core.validators
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('api', '0005_alter_attachment_file_alter_attachment_mou_nda_and_more'),
    ]

    operations = [
        migrations.AddField(
            model_name='mou_nda',
            name='position_level',
            field=models.IntegerField(default=1, max_length=1, validators=[django.core.validators.MaxValueValidator(6), django.core.validators.MinValueValidator(1)]),
        ),
        migrations.AddField(
            model_name='pks',
            name='position_level',
            field=models.IntegerField(default=1, max_length=1, validators=[django.core.validators.MaxValueValidator(6), django.core.validators.MinValueValidator(1)]),
        ),
    ]