package com.example.liangmutian.myapplication2;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


public class LowStorageDialog extends Dialog {
    private static final String TAG = "powersavingmanager";


    public LowStorageDialog(Context context) {
        super(context, R.style.bottom_diaolog_anim_style);
    }

    public static class Builder{

        private Context context;
        private LowStorageDialog lowStorageDialog;
        private String contentText;
        private String leftText;
        private String rightText;
        private View.OnClickListener leftClickListener;
        private View.OnClickListener rightClickListener;
        private View decorView;
        private Drawable bgDrawable;
        private TextView content;


        public Builder(Context context) {
            super();
            this.context = context;
        }

        public Builder initBtn(){
            this.leftClickListener = null;
            this.leftText = null;
            this.rightClickListener = null;
            this.rightText = null;

            return this;
        }

        public Builder setContent(String contentText){
            this.contentText = contentText;
            return this;
        }
        public Builder setContent(int contentText){
            this.contentText = context.getText(contentText).toString();
            return this;
        }

        public Builder setPositiveBtn(String positiveBtnText,
                                      View.OnClickListener clickListener) {
            this.rightText = positiveBtnText;
            this.rightClickListener = clickListener;
            return this;
        }

        public Builder setPositiveBtn(int positiveBtnText,
                                      View.OnClickListener clickListener) {
            this.rightText = (String) context.getText(positiveBtnText);
            this.rightClickListener = clickListener;
            return this;
        }

        public Builder setNegativeBtn(String negativeBtn,
                                      View.OnClickListener clickListener) {
            this.leftText = negativeBtn;
            this.leftClickListener = clickListener;
            return this;
        }

        public Builder setNegativeBtn(int negativeBtn,
                                      View.OnClickListener clickListener) {
            this.leftText = (String) context.getText(negativeBtn);
            this.leftClickListener = clickListener;
            return this;
        }


        public LowStorageDialog build() {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View contentView = inflater.inflate(R.layout.system_alert_dialog, null);
            lowStorageDialog = new LowStorageDialog(context);
            content = (TextView) contentView.findViewById(R.id.tv_pop_content);
            TextView left = (TextView) contentView.findViewById(R.id.tv_pop_left);
            TextView right = (TextView) contentView.findViewById(R.id.tv_pop_right);


            if (leftText != null) {
                left.setText(leftText);
                if (leftClickListener != null) {
                    left.setOnClickListener(leftClickListener);
                } else {
                    left.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            lowStorageDialog.dismiss();
                        }
                    });
                }
            } else {
                left.setVisibility(View.GONE);
            }
            if (rightText != null) {
                right.setText(rightText);
                if (rightClickListener != null) {
                    right.setOnClickListener(rightClickListener);
                } else {
                    right.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            lowStorageDialog.dismiss();
                        }
                    });
                }
            } else {
                right.setVisibility(View.GONE);
            }

            if (contentText != null) {
                content.setText(contentText);
            }
            Window win = lowStorageDialog.getWindow();
            win.getDecorView().setPadding(0,0,0,0);
            WindowManager.LayoutParams lp = win.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            win.setAttributes(lp);
            // win.setGravity(Gravity.NO_GRAVITY);
            win.setGravity(Gravity.BOTTOM);
            lowStorageDialog.setContentView(contentView);
            lowStorageDialog.setCanceledOnTouchOutside(true);
            // lowStorageDialog.setCancelable(false);

            return lowStorageDialog;

        }

        public void dismiss() {
            if (lowStorageDialog != null) {
                if(isShowing()) {
                    lowStorageDialog.dismiss();
                }
                lowStorageDialog = null;
            }
        }
        public boolean isShowing() {
            boolean isShow = false;
            if (lowStorageDialog != null) {
                isShow = lowStorageDialog.isShowing();
            }
            return isShow;
        }

    }






}
