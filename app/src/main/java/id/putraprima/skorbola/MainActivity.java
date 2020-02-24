package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import static id.putraprima.skorbola.MainActivity.InputHome;
import static id.putraprima.skorbola.MainActivity.InputAway;
import static id.putraprima.skorbola.MainActivity.LogoHome;
import static id.putraprima.skorbola.MainActivity.LogoAway;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG=MainActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE=1 & 2;
    private static final int GALLERY_REQUEST=1;
    public static final String InputHome="home";
    public static final String InputAway="away";
    public static final String LogoHome="logohome";
    public static final String LogoAway="logoaway";
    private EditText inputHome;
    private EditText inputAway;
    private ImageView logoHome;
    private ImageView logoAway;
    private Uri imageUri1 = null;
    private Uri imageUri2 = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputHome=findViewById(R.id.home_team);
        inputAway=findViewById(R.id.away_team);
        logoHome=findViewById(R.id.home_logo);
        logoAway=findViewById(R.id.away_logo);

        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
    }

    public void buttonNext(View view) {
        String home=inputHome.getText().toString();
        String away=inputAway.getText().toString();

        if(home.isEmpty()){
            inputHome.setError("Fill home team name");
        } else if(away.isEmpty()){
            inputAway.setError("Fill away team name");
        } else {
            Intent intent = new Intent(this, MatchActivity.class);
            intent.putExtra(InputHome, home);
            intent.putExtra(InputAway, away);
            logoHome.buildDrawingCache();
            Bitmap image=logoHome.getDrawingCache();
            Bundle extras=new Bundle();
            extras.putParcelable("LogoHome", image);
            intent.putExtras(extras);
            logoAway.buildDrawingCache();
            Bitmap img=logoAway.getDrawingCache();
            Bundle ext=new Bundle();
            ext.putParcelable("LogoAway", img);
            intent.putExtras(ext);
            startActivity(intent);
        }

    }

    public void imageHome(View view) {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public void imageAway(View view) {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_CANCELED){
            return;
        }
        if(requestCode==1){
            if(data!=null){
                try{
                    imageUri1 = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri1);
                    logoHome.setImageBitmap(bitmap);
                } catch (IOException e){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG,e.getMessage());
                }
            }

        }
        if(requestCode==2){
            if(data!=null){
                try{
                    imageUri2 = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri2);
                    logoAway.setImageBitmap(bitmap);
                } catch (IOException e){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG,e.getMessage());
                }
            }

        }

    }
//


}
