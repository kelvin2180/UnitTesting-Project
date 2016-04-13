package tests;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.TextView;

import com.example.wangzhe.testproject2.MainActivity;

/**
 * Created by wangzhe on 4/13/16.
 */
public class JUnit_test extends ActivityInstrumentationTestCase2 <MainActivity>{

    MainActivity mainActivity;

    public JUnit_test()
    {
        super (MainActivity.class);

    }


    @SmallTest
    public void test_first()
    {
        mainActivity = getActivity();

        TextView textView = (TextView) mainActivity.findViewById(com.example.wangzhe.testproject2.R.id.tv1);
        String tester = textView.getText().toString();
        assertEquals("Bye World", tester);
    }
}
