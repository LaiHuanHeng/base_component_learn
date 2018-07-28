package com.example.fragment_learn1;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {

//    private String content;

    public MyFragment(){}

//    public MyFragment(String content) {
//        this.content = content;
//    }

    public static MyFragment newInstance(String content) {
        MyFragment newFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("content", content);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content,container,false);
        TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        String content = "";
//        txt_content.setText(content);
        Bundle args = getArguments();
        if (args != null) {
            content = args.getString("content");
        }
        txt_content.setText(content);
        return view;
    }

}
