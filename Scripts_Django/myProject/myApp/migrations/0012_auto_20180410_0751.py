# Generated by Django 2.0.3 on 2018-04-10 07:51

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('myApp', '0011_auto_20180410_0741'),
    ]

    operations = [
        migrations.AlterField(
            model_name='user',
            name='sex',
            field=models.TextField(default='', max_length=4),
        ),
    ]
