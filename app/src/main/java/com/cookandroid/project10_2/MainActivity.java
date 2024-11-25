package com.cookandroid.project10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("영화 선호도 투표");

        final int voteCount[] = new int[9];
        for(int i=0; i<voteCount.length; i++) {
            voteCount[i] = 0;
        }

        // 9개의 이미지 버튼 객체배열
        ImageView image[] = new ImageView[9];
        // 9개의 이미지버튼 ID 배열
        Integer imageId[] = { R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv5,
                R.id.iv6, R.id.iv7, R.id.iv8, R.id.iv9 };
        String imgName[] = {"독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀",
                "이레느깡 단 베르양", "잠자는 소녀", "테라스의 두 자매", "피아노 레슨",
                "피아노 앞의 소녀들", "해변에서"};
    // i 는 클래스 밖에 선언되어 있기 때문에 final타입으로 바꿔서 사용해야 오류가 뜨지 않음
        // final 쓰는 건 java에서 주의해야함
        for (int i=0; i<imageId.length; i++) {
            final  int index;       // 꼭 필요함. 안쓰면 i 에서 오류
            index = i;
            image[i] = (ImageView) findViewById(imageId[i]);
            image[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    voteCount[index]++;
                    Toast.makeText(getApplicationContext(), "총" + voteCount[index] + "표",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }

        Button btnResult = (Button) findViewById(R.id.btnResult);
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("VoteCount", voteCount);
                intent.putExtra("imageName", imgName);

                startActivity(intent);
            }
        });

    }

}