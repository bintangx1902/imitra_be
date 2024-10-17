from django.db.models import Model


def count_details(mou_nda: Model, pks: Model) -> int:
    return mou_nda.objects.count() + pks.objects.count()


def count_revision(mou_nda: Model, pks: Model) -> int:
    return mou_nda.objects.filter(status='r').count() + pks.objects.filter(status='r').count()


def count_rejected(mou_nda: Model, pks: Model) -> int:
    return mou_nda.objects.filter(status='t').count() + pks.objects.filter(status='t').count()


def count_processed(mou_nda: Model, pks: Model) -> int:
    return mou_nda.objects.filter(status='p').count() + pks.objects.filter(status='p').count()


def count_finish(mou_nda: Model, pks: Model) -> int:
    return mou_nda.objects.filter(status='f').count() + pks.objects.filter(status='f').count()
