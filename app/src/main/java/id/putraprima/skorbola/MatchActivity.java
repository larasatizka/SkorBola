package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {
    private static final String TAG=MainActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE=1;
    public static final String InputHome="home";
    public static final String InputAway="away";
    public static final String LogoHome="logohome";
    public static final String LogoAway="logoaway";
    public static final String HasilHome="hasilhome";
    public static final String HasilAway="hasilaway";
    public static final String HasilDraw="hasildraw";
    private TextView inputHome;
    private TextView inputAway;
    private TextView scoreHome;
    private TextView scoreAway;
    private ImageView logoHome;
    private ImageView logoAway;
    private Button addHome;
    private Button addAway;
    private Uri imageUri = null;
    private int nilaiHome, nilaiAway=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO

        inputHome=findViewById(R.id.txt_home);
        inputAway=findViewById(R.id.txt_away);
        logoHome=findViewById(R.id.home_logo);
        logoAway=findViewById(R.id.away_logo);
        scoreHome=findViewById(R.id.score_home);
        scoreAway=findViewById(R.id.score_away);
        addHome=findViewById(R.id.btn_add_home);
        addAway=findViewById(R.id.btn_add_away);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
//            // TODO: display value here
            String value=getIntent().getExtras().getString(InputHome);
            String value1=getIntent().getExtras().getString(InputAway);

            inputHome.setText(value);
            inputAway.setText(value1);
//
        }

        Bitmap bmp=(Bitmap)extras.getParcelable("LogoHome");
        logoHome.setImageBitmap(bmp);

        Bitmap bit=(Bitmap)extras.getParcelable("LogoAway");
        logoAway.setImageBitmap(bit);

        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    }

    public void displayTeamHome(int score1){
        scoreHome.setText(String.valueOf(score1));
    }

    public void displayTeamAway(int score2){
        scoreAway.setText(String.valueOf(score2));
    }

    public void addHome(View view) {
        nilaiHome=nilaiHome+1;
        displayTeamHome(nilaiHome);
    }

    public void addAway(View view) {
        nilaiAway=nilaiAway+1;
        displayTeamAway(nilaiAway);
    }

    public void cekHasil(View view) {
        Intent in = new Intent(this, ResultActivity.class);
        String home=inputHome.getText().toString();
        String away=inputAway.getText().toString();

        if (nilaiHome>nilaiAway){
            in.putExtra(HasilHome, home);
            startActivity(in);
        } else if(nilaiAway>nilaiHome){
            in.putExtra(HasilHome, away);
            startActivity(in);
        } else{
            in.putExtra(HasilHome, "HASIL DRAW");
            startActivity(in);
        }
    }
}
