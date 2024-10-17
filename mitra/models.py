from django.db import models

from api.models import Mou_Nda, PKS


class Proposal(models.Model):
    file = models.FileField(upload_to='proposal')
    mou_nda = models.ForeignKey(Mou_Nda, on_delete=models.CASCADE,
                                related_name='proposal_mou', related_query_name='proposal_mou')
    pks = models.ForeignKey(PKS, on_delete=models.CASCADE,
                            related_name='proposal_pks', related_query_name='proposal_pks')
