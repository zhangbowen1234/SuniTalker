package com.silver.chat.ui.mine.setting;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.base.Common;
import com.silver.chat.network.SSIMLoginManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.UpdateUserInfoBean;
import com.silver.chat.util.GetFilesUtils;
import com.silver.chat.util.GlideUtil;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.CircleImageView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.silver.chat.util.Utils.context;

/**
 * 作者：Fandy on 2016/12/9 11:34
 * 邮箱：fandy618@hotmail.com
 * <p>
 * 账户
 */

public class AccountActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 返回
     */
    private ImageView mIvBack;
    /**
     * 完成
     */
    private TextView mTvFinish;
    /**
     * 头像
     */
    private RelativeLayout mRlyAvatar;
    private CircleImageView mIvAvatar;
    /**
     * 名字
     */
    private LinearLayout mLlyName;
    private EditText mTvName, mEdPhone;
    private String spName, nickName, spPhone;
    private Dialog dialog = null;
    private Uri imageUri = null;
    private String path;// 不含图片的文件目录
    private String imagePath;// 包含图片的路径
    private String imageName = "imHead.jpg";
    private File tempFile;
    private String iconUrl;//网络图片地址

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account;
    }

    @Override
    protected void initView() {
        super.initView();
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mTvFinish = (TextView) findViewById(R.id.iv_save);
        mRlyAvatar = (RelativeLayout) findViewById(R.id.rly_avatar);
        mIvAvatar = (CircleImageView) findViewById(R.id.iv_avatar);
        mLlyName = (LinearLayout) findViewById(R.id.lly_name);
        mTvName = (EditText) findViewById(R.id.tv_name);
        mEdPhone = (EditText) findViewById(R.id.tv_phone);
        // 头像存储路径
        path = GetFilesUtils.getFolderPath(mContext, Common.IMAGE_PATH);
        //文件全路径
        imagePath = path + imageName;

        tempFile = new File(path);
        if (!(tempFile.exists())) {
            tempFile.mkdirs();
        }
        //显示头像
        iconUrl = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.AVATAR, "");
        GlideUtil.loadAvatar(mIvAvatar, iconUrl);

    }

    @Override
    protected void initListener() {
        super.initListener();
        mIvBack.setOnClickListener(this);
        mTvFinish.setOnClickListener(this);
        mRlyAvatar.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        spName = PreferenceUtil.getInstance(mContext).getString("nickName", "");
        spPhone = PreferenceUtil.getInstance(mContext).getString("phone", "");
        mTvName.setText(spName);
        mEdPhone.setText(spPhone);

        mTvName.setSelection(mTvName.getText().length());
        mEdPhone.setSelection(mEdPhone.getText().length());
        mTvName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Editable editable = mEdPhone.getText();
                int len = editable.length();
                //得到最初字段的长度大小，用于光标位置的判断
                int selEndIndex = Selection.getSelectionEnd(editable);
                // 取出每个字符进行判断，如果是字母数字和标点符号则为一个字符加1，
                // 当最大字符大于11时，进行字段的截取，并进行提示字段的大小
                if (len > 11) {
                    String str = editable.toString();
                    // 截取最大的字段
                    String newStr = str.substring(0, 11);
                    mEdPhone.setText(newStr);
                    // 得到新字段的长度值
                    editable = mEdPhone.getText();
                    int newLen = editable.length();
                    if (selEndIndex > newLen) {
                        selEndIndex = editable.length();
                    }
                    // 设置新光标所在的位置
                    Selection.setSelection(editable, selEndIndex);
                    ToastUtils.showMessage(context, "至多输入11个字");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mEdPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_save:
                getUpdateInfo();
                finish();
                break;
            case R.id.rly_avatar:
                showDialog();
                break;
            case R.id.btn_camera://相机
                openCamera();
                dialog.dismiss();
                break;
            case R.id.btn_gallery:
                openGallery();
                dialog.dismiss();
                break;
        }
    }

    private void showDialog() {
        dialog = new Dialog(mContext, R.style.AlertDialogStyle);
        dialog.setContentView(R.layout.pw_head);
        dialog.show();
        // 将自定义的对话框布局写在这里
        System.out.println("=======自定义对话框设置完毕，下面开始设置对话框中的信息=========");
        final TextView camera = (TextView) dialog.findViewById(R.id.btn_camera);
        final TextView photo = (TextView) dialog.findViewById(R.id.btn_gallery);
        camera.setOnClickListener(this);
        photo.setOnClickListener(this);
    }

    /**
     * 调用相机
     */
    public void openCamera() {
        imageUri = Uri.fromFile(new File(path, imageName));
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        // 保存照片在自定义的文件夹下面
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, Common.OPEN_CAMERA_CODE);

    }

    /**
     * 打开相册
     */
    public void openGallery() {
        imageUri = Uri.fromFile(new File(path, imageName));
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        // 保存照片在自定义的文件夹下面
        startActivityForResult(intent, Common.OPEN_GALLERY_CODE);
    }

    /**
     * 裁剪图片
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", true);
        // 设置裁剪尺寸
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        // 保存照片在自定义的文件夹下面
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, Common.CROP_PHOTO_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Uri uri = null;
        if (data == null) {
            uri = imageUri;
        } else {
            uri = data.getData();
        }
        System.out.println("===========uri========" + uri);
        if (resultCode == RESULT_OK) {

            switch (requestCode) {
                case Common.OPEN_CAMERA_CODE:
                    // 取消选择的时候直接返回，下同
                    cropPhoto(uri);
                    break;
                case Common.OPEN_GALLERY_CODE:
                    cropPhoto(uri);
                    break;
                case Common.CROP_PHOTO_CODE:
                    //上传头像
                    upLoad();
                    break;
                default:
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private Map<String, String> headers = new HashMap<>();
//    private Novate novate;
    /**
     * 上传头像
     */
    private void upLoad() {

//        //创建RequestBody,用于封装构建RequestBody
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imagePath);
//        // MultipartBody.Part  和后端约定好Key，这里的partName是用image
//        MultipartBody.Part body = MultipartBody.Part.createFormData("file", imageName, requestFile);

//        headers.put("token",PreferenceUtil.TOKEN);
//        novate = new Novate.Builder(this)
//                //.addParameters(parameters)
//                .connectTimeout(20)
//                .writeTimeout(15)
//                .baseUrl(Urls.CLOUD_SERVER)
//                .addHeader(headers)//.addApiManager(ApiManager.class)
//                .addLog(true)
//                .build();


    }

    public void getUpdateInfo() {
        nickName = mTvName.getText().toString();
        if (Objects.equals(nickName, spName) || spName.equals(nickName)) {
            //ToastUtils.showMessage(mContext,"无需重复请求");
            return;
        }
        String token = PreferenceUtil.getInstance(mContext).getString(PreferenceUtil.TOKEN, "");
        UpdateUserInfoBean instance = UpdateUserInfoBean.getInstance();
        instance.setNickName(mTvName.getText().toString());
        instance.setSex(1);
        instance.setAge(18);
        instance.setSignature("生命在于运动，啪啪啪");

        SSIMLoginManger.updateUserInfo(Common.version, token, instance, new ResponseCallBack<BaseResponse<UpdateUserInfoBean>>() {
            @Override
            public void onSuccess(BaseResponse<UpdateUserInfoBean> userInfoBeanBaseResponse) {
                Log.e("getUserInfo", userInfoBeanBaseResponse.getStatusMsg());
            }

            @Override
            public void onFailed(BaseResponse<UpdateUserInfoBean> userInfoBeanBaseResponse) {
            }

            @Override
            public void onError() {
            }
        });
    }
}
