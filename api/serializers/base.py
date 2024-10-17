# serializers.py

from rest_framework import serializers
from api.models import *


class ScopeSerializer(serializers.ModelSerializer):
    class Meta:
        model = Scope
        fields = ['scope_name']


class AttachmentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Attachment
        fields = ['file']


class RabSerializer(serializers.ModelSerializer):
    class Meta:
        model = RAB
        fields = '__all__'


class MouNdaSerializer(serializers.ModelSerializer):
    scopes_mou = ScopeSerializer(many=True, required=False)
    attachments_mou = AttachmentSerializer(many=True, required=False)

    class Meta:
        model = Mou_Nda
        fields = '__all__'


class PKSSerializer(serializers.ModelSerializer):
    rab = RabSerializer(many=True, required=False)
    scopes_pks = ScopeSerializer(many=True, required=False)
    attachments_pks = AttachmentSerializer(many=True, required=False)

    class Meta:
        model = PKS
        fields = '__all__'
