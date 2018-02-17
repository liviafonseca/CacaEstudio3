package br.com.pos.cacaestudio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AvaliarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliar);

        Button btn = findViewById(R.id.avaliar_btn_enviar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AvaliarActivity.this, "Futuramente este ação irá" +
                        "acrescentar um novo comentrário",
                        Toast.LENGTH_LONG).show();

            }
        });
    }
}
