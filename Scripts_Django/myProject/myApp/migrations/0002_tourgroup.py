# Generated by Django 2.0.3 on 2018-03-29 03:45

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('myApp', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='TourGroup',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('title', models.CharField(max_length=32)),
                ('desc', models.TextField(null=True)),
                ('content_url', models.GenericIPAddressField()),
                ('picture', models.ImageField(upload_to='')),
                ('pub_time', models.DateTimeField(auto_now=True)),
            ],
        ),
    ]
