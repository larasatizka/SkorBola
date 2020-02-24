package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView hasil;
    public static final String HasilHome="hasilhome";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        hasil=findViewById(R.id.textView3);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
//            // TODO: display value here
            String value=getIntent().getExtras().getString(HasilHome);

            hasil.setText(value);
//
        }
    }
}
