# Generated by Django 2.0.3 on 2018-04-10 07:41

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('myApp', '0010_auto_20180410_0739'),
    ]

    operations = [
        migrations.AlterField(
            model_name='user',
            name='headprotrait_url',
            field=models.CharField(blank=True, default='', max_length=128, null=True),
        ),
        migrations.AlterField(
            model_name='user',
            name='name',
            field=models.CharField(max_length=32, unique=True),
        ),
    ]
