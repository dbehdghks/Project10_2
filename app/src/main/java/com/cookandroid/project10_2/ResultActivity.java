package com.cookandroid.project10_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ResultActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        setTitle("투표결과");

        Intent intent = getIntent();    // 도착
        // 타입이 지정된 메소드로 불러와야함
        int [] voteResult = intent.getIntArrayExtra("VoteCount");   // 도착한 인텐트
        String[] imageName = intent.getStringArrayExtra("imageName");  // 그림

        // 가장 많은 표를 받은 그림 찾기
        int maxVotes = 0;
        int maxIndex = 0;
        for (int i = 0; i < voteResult.length; i++) {
            if (voteResult[i] > maxVotes) {
                maxVotes = voteResult[i];
                maxIndex = i;
            }
        }
        // 상단에 표시할 이미지와 텍스트 설정
        ImageView topImageView = (ImageView) findViewById(R.id.topImageView);
        TextView topImageText = (TextView) findViewById(R.id.topImageText);

        Integer[] imageResIds = {
                R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
                R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,
                R.drawable.pic7, R.drawable.pic8, R.drawable.pic9
        };

        topImageView.setImageResource(imageResIds[maxIndex]); // 가장 많은 표를 받은 그림 설정
        topImageText.setText(imageName[maxIndex]); // 제목 설정

        TextView tv[] = new TextView[imageName.length];     //텍스트뷰 멤버변수 지정
        RatingBar rbar[] = new RatingBar[imageName.length];     // 레이팅바 멤버변수 지정

        Integer tvID[] = { R.id.tv1, R.id.tv2,R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9 };
        Integer rbarID[] = { R.id.rbar1, R.id.rbar2, R.id.rbar3, R.id.rbar4, R.id.rbar5, R.id.rbar6, R.id.rbar7, R.id.rbar8, R.id.rbar9 };

        // TextView, RatingBar 개체 찾기.
        for(int i=0; i < imageName.length; i++) {     // TextView, Rating 인스턴스 정의
            tv[i] = (TextView) findViewById(tvID[i]);
            rbar[i] = (RatingBar) findViewById(rbarID[i]);
        }

        // 각 TextVeiw 및 RatingBar에 넘겨 받은 값을 반영.
        for(int i=0; i < voteResult.length; i++) {       // 그림 제목을 텍스트뷰에 표시
            tv[i].setText(imageName[i]);
            rbar[i].setRating((float) voteResult[i]);
        }

        Button btnReturn = (Button) findViewById(R.id.btnReturn);   // 돌아가기 버튼
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
