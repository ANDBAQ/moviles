/**
private static final int RESULT_LOAD_CAMERA = 105;

public void btnCapture(View view) {

        Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(photoIntent, RESULT_LOAD_CAMERA);
        }

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != 0 && resultCode == RESULT_OK) {
        if (requestCode == RESULT_LOAD_CAMERA) {

        Bitmap bp = (Bitmap) data.getExtras().get("data");
        file = saveImage(bp, this);

        }
        }
        }

private java.io.File saveImage(Bitmap myBitmap, Context context) {

        java.io.File myDir = new java.io.File(Environment.getExternalStorageDirectory(), context.getPackageName());
        if (!myDir.exists()) {
        myDir.mkdir();
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nameImage = "IMG_" + timeStamp + ".png";

        java.io.File file = new java.io.File(myDir, nameImage);

        try {
        FileOutputStream out = new FileOutputStream(file);
        myBitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
        out.flush();
        out.close();

        } catch (Exception e) {
        e.printStackTrace();
        }
        return file;
        }*/