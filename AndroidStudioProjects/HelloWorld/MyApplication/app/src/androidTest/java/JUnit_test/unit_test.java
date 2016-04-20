package JUnit_test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wangzhe.myapplication.MainActivity;
import com.example.wangzhe.myapplication.R;

/**
 * Created by wangzhe on 4/20/16.
 */
public class unit_test extends ActivityInstrumentationTestCase2<MainActivity>{

    MainActivity mainActivity;

    public unit_test() {

        super(MainActivity.class);
    }

    @UiThreadTest
    public void test_first()
    {

        mainActivity = getActivity();


        Button btn = (Button) mainActivity.findViewById(R.id.butA);
        TextView textView = (TextView) mainActivity.findViewById(R.id.sumC);
        EditText editText1 = (EditText) mainActivity.findViewById(R.id.numA);
        EditText editText2 = (EditText) mainActivity.findViewById(R.id.numB);

        editText1.setText("33.",TextView.BufferType.EDITABLE);
        editText2.setText("44.",TextView.BufferType.EDITABLE);

        btn.performClick();



        double a = Double.parseDouble(editText1.getText().toString());
        double b = Double.parseDouble(editText2.getText().toString());

        assertEquals(Double.parseDouble(textView.getText().toString()),a+b);


    }
}

