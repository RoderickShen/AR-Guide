# Generated by Django 2.0.3 on 2018-05-28 06:40

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('myApp', '0016_message'),
    ]

    operations = [
        migrations.AlterField(
            model_name='message',
            name='user',
            field=models.CharField(default='', max_length=32),
        ),
    ]
