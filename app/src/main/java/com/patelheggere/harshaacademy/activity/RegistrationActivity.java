package com.patelheggere.harshaacademy.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.patelheggere.harshaacademy.R;
import com.patelheggere.harshaacademy.base.BaseActivity;
import com.patelheggere.harshaacademy.model.APIResponseModel;
import com.patelheggere.harshaacademy.network.ApiInterface;
import com.patelheggere.harshaacademy.network.RetrofitInstance;
import com.patelheggere.harshaacademy.utils.SharedPrefsHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.patelheggere.harshaacademy.utils.Constants.FIRST_TIME;
import static com.patelheggere.harshaacademy.utils.Constants.NAME;
import static com.patelheggere.harshaacademy.utils.Constants.USER_NAME;

public class RegistrationActivity extends BaseActivity {
    private static final String TAG = "RegistrationActivity";
    private ActionBar mActionBar;
    private TextInputEditText textInputEditTextName, textInputEditTextPhone,textInputEditTextPhoneLogin, textInputEditTextEmail, textInputEditTextPwd, textInputEditTextPwdLogin;
    private String course;
    private List<String> listInterest;
    private ArrayAdapter<String> adapter;
    private Button mRegisterSubmit, mButtonLoginSubmit, mButtonLogin, mButtonRegister;
    private View registerView, loginView;
    private ProgressBar mProgressBar;
    private Button btn_upload;
    private TextView mTextViewForgot;
    private String[] PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            /* android.Manifest.permission.RECORD_AUDIO,*/
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    int PERMISSION_ALL = 1;
    private final int PICK_IMAGE_REQUEST = 71;
    private final int PICK_IMAGE_REQUEST_2 = 1111;
    private final int PICK_VIDEO_REQUEST_2 = 2222;
    private static final int SELECT_FILE = 1100;
    private static final int REQUEST_CAMERA = 1200;
    private static final int SELECT_VIDEO = 1300;
    private static final int REQUEST_CODE_DOC = 1400;
    private static final int RQS_RECORDING = 1500;
    private static final int VIDEO_CAPTURE = 1600;
    private static final int AUDIO_LOCAL = 1700;

    private ApiInterface apiInterface;
    private List<String> mDistrictNamesList;
    private List<String> mAssemblyNamesList;
    private List<String> mTalukNames;
    private List<String> mTandaNames;
    private Spinner mSpinnerDistrict, mSpinnerAssembly, mSpinnerTaluk, mSpinnerVillage;
    private String dist=null, taluk=null, village=null, tandaRes = "yes", desiredJob=null, qual=null;

    @Override
    protected int getContentView() {
        return R.layout.activity_registration;
    }

    @Override
    protected void initView() {
        addListofInterest();
        mActionBar = getSupportActionBar();
        mActionBar.setTitle(getString(R.string.login));

      /*  if (!AppUtils.hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }*/
        mProgressBar = findViewById(R.id.progress_bar);

        textInputEditTextPhoneLogin = findViewById(R.id.et_phone_login);
        textInputEditTextPwd = findViewById(R.id.et_pwd);
        textInputEditTextPwdLogin = findViewById(R.id.et_pwd_login);

        mRegisterSubmit = findViewById(R.id.btn_register_submit);
        mButtonRegister = findViewById(R.id.btn_register);
        mButtonLogin = findViewById(R.id.btn_login);
        mButtonLoginSubmit = findViewById(R.id.btn_login_submit);
        registerView = findViewById(R.id.reg_lyt);
        loginView = findViewById(R.id.log_lyt);
    }

    private void addListofInterest() {
        listInterest = new ArrayList<>();
        listInterest.add("Select one");
        listInterest.add("KAS");
        listInterest.add("PDO");
        listInterest.add("FDA");
        listInterest.add("SDA");
        listInterest.add("BANKING");
        listInterest.add("TET");
        listInterest.add("CET");
        listInterest.add("NEET");
        listInterest.add("POLICE");
        listInterest.add("PSI");
        listInterest.add("KES");
        listInterest.add("OTHERS");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        setUpNetwork();

        mRegisterSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  submitDetails();
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerView.setVisibility(View.GONE);
                loginView.setVisibility(View.VISIBLE);
                mActionBar.setTitle(getString(R.string.login));
            }
        });


        mButtonLoginSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitLoginDetails();
            }
        });



    }

    private void submitLoginDetails() {

        if(textInputEditTextPhoneLogin.getText()==null || textInputEditTextPhoneLogin.getText().length()<5)
        {
            textInputEditTextPhoneLogin.setError(getString(R.string.correct_username));
            return;
        }
        if(textInputEditTextPwdLogin.getText()==null || textInputEditTextPwdLogin.getText().length()<3)
        {
            textInputEditTextPwdLogin.setError(getString(R.string.enter_pwd));
            return;
        }
        mButtonLoginSubmit.setEnabled(false);
        mButtonLoginSubmit.setClickable(false);
        mProgressBar.setVisibility(View.VISIBLE);

        Call<APIResponseModel> userVerifyCall = apiInterface.verifyUser(textInputEditTextPhoneLogin.getText().toString(), textInputEditTextPwdLogin.getText().toString());
        userVerifyCall.enqueue(new Callback<APIResponseModel>() {
            @Override
            public void onResponse(Call<APIResponseModel> call, Response<APIResponseModel> response) {
                mProgressBar.setVisibility(View.GONE);
                if(response.isSuccessful() && response.body().isStatus())
                {
                        SharedPrefsHelper.getInstance().save(FIRST_TIME, false);
                        SharedPrefsHelper.getInstance().save(NAME, response.body().getName());
                        SharedPrefsHelper.getInstance().save(USER_NAME, response.body().getPhone());
                        Intent intent = new Intent(activity, MainActivity.class);
                        startActivity(intent);
                        finish();
                }else{
                    mButtonLoginSubmit.setEnabled(true);
                    mButtonLoginSubmit.setClickable(true);
                    showToast(getString(R.string.invalid));
                }
            }

            @Override
            public void onFailure(Call<APIResponseModel> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                mButtonLoginSubmit.setEnabled(true);
                mButtonLoginSubmit.setClickable(true);
                showToast(getString(R.string.something_wrong));
            }
        });


    }

    private void showToast(String string) {
        Toast.makeText(RegistrationActivity.this, string, Toast.LENGTH_LONG).show();
    }

    private void setUpNetwork()
    {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        retrofitInstance.setClient();
        apiInterface = retrofitInstance.getClient().create(ApiInterface.class);
    }



    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST_2);
    }

    private void chooseVideo() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Video"), PICK_VIDEO_REQUEST_2);
    }

    private boolean hasCamera() {
        if (getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_ANY)) {
            return true;
        } else {
            return false;
        }
    }
    private void galleryIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }


    private File mediaFile;


}