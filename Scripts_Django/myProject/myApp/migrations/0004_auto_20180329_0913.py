# Generated by Django 2.0.3 on 2018-03-29 09:13

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('myApp', '0003_auto_20180329_0400'),
    ]

    operations = [
        migrations.AlterField(
            model_name='tourgroup',
            name='picture',
            field=models.CharField(max_length=32),
        ),
    ]
