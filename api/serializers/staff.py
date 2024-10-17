from rest_framework import serializers
from .base import *


class PostMouNdaSerializer(serializers.Serializer):
    base = serializers.CharField(max_length=10)
    partnership_title = serializers.CharField(max_length=255)
    background = serializers.CharField()
    note = serializers.CharField(required=False)
    user = serializers.PrimaryKeyRelatedField(queryset=User.objects.all())
    status = serializers.ChoiceField(choices=status_choices)
    response_text = serializers.CharField(required=False, allow_blank=True)
    approval_note = serializers.CharField(required=False, allow_blank=True)
    mou_nda_number = serializers.CharField(max_length=50, required=False, allow_blank=True)
    approval_completion_date = serializers.DateTimeField(required=False, allow_null=True)
    official_undersign = serializers.PrimaryKeyRelatedField(queryset=Functionary.objects.all(), required=False,
                                                            allow_null=True)

    scopes = ScopeSerializer(many=True, required=False)
    attachments = AttachmentSerializer(many=True, required=False)

    def create(self, validated_data):
        scopes_data = validated_data.pop('scopes', [])
        attachments_data = validated_data.pop('attachments', [])

        mou_nda = Mou_Nda.objects.create(**validated_data)

        for scope_data in scopes_data:
            Scope.objects.create(mou_nda=mou_nda, **scope_data)

        for attachment_data in attachments_data:
            Attachment.objects.create(mou_nda=mou_nda, **attachment_data)

        return mou_nda


