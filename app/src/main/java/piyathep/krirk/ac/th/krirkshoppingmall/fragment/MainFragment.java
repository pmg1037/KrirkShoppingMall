package piyathep.krirk.ac.th.krirkshoppingmall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import piyathep.krirk.ac.th.krirkshoppingmall.R;
import piyathep.krirk.ac.th.krirkshoppingmall.utility.GetAllDataByURL;
import piyathep.krirk.ac.th.krirkshoppingmall.utility.MyAlert;
import piyathep.krirk.ac.th.krirkshoppingmall.utility.MyConstant;

/**
 * Created by Piyathep on 6/3/2561.
 */

public class MainFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Register Controller
        registerController();

//        Login Controller
        loginController();

    }   // Main Method

    private void loginController() {
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

                String userString = userEditText.getText().toString().trim();
                String passwordString = passwordEditText.getText().toString().trim();

                if (userString.isEmpty()|| passwordString.isEmpty()) {
//                    Have Space
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog(getString(R.string.title_have_space),
                            getString(R.string.msg_have_space));
                } else {
//                    No Space
                    try {

                        MyConstant myConstant = new MyConstant();
                        boolean statusAboolean = true;
                        GetAllDataByURL getAllDataByURL = new GetAllDataByURL(getActivity());
                        getAllDataByURL.execute(myConstant.getUrlGetAllUserString());

                        String jsonString = getAllDataByURL.get();
                        Log.d("27MarchV1", "JSON ==> " + jsonString);

                        JSONArray jsonArray = new JSONArray(jsonString);

                        String[] columnStrings = myConstant.getColumnUser();
                        String[] loginStrings = new String[columnStrings.length];

                        for (int i=0; i<jsonArray.length(); i+=1) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if (userString.equals(jsonObject.getString(columnStrings[2]))) {

                                statusAboolean = false;
                                for (int i1=0; i1<columnStrings.length; i1+=1) {
                                    loginStrings[i1] = jsonObject.getString(columnStrings[i1]);
                                }

                            }   // if

                        }   // for

                        if (statusAboolean) {
//                            User False
                            MyAlert myAlert = new MyAlert(getActivity());
                            myAlert.myDialog("User False", "No This User in my Database");
                        } else if (passwordString.equals(loginStrings[3])) {
//                            password True
                            Toast.makeText(getActivity(), "Welcome " + loginStrings[1], Toast.LENGTH_SHORT).show();
                        } else {
//                            passwoed False
                            MyAlert myAlert = new MyAlert(getActivity());
                            myAlert.myDialog("Password False", "Please Try Again Password False");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }   // if

            }
        });
    }

    private void registerController() {
        TextView textView = getView().findViewById(R.id.txtNewRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Replace Fragment
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }
}
