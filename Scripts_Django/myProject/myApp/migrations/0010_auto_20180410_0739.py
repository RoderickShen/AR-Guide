# Generated by Django 2.0.3 on 2018-04-10 07:39

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('myApp', '0009_auto_20180410_0735'),
    ]

    operations = [
        migrations.AlterField(
            model_name='user',
            name='headprotrait_url',
            field=models.CharField(default='', max_length=128, null=True),
        ),
    ]
