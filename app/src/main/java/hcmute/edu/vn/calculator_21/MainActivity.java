package hcmute.edu.vn.calculator_21;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine;
    Button btnClear, btnComma, btnEqual, btnPlus, btnSubtract, btnMultiple, btnDivide;
    TextView txtDisplay;

    Boolean newInput = true;
    String calString ="";
    StringCalculator cal = new StringCalculator(); // Một class xử lý các vấn đề toán học và chuỗi giá trị

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getComponent();

        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberHandle("0");
            }
        });
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberHandle("1");
            }
        });
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberHandle("2");
            }
        });
        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberHandle("3");
            }
        });
        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberHandle("4");
            }
        });
        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberHandle("5");
            }
        });
        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberHandle("6");
            }
        });
        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberHandle("7");
            }
        });
        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberHandle("8");
            }
        });
        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberHandle("9");
            }
        });

        btnComma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmp = txtDisplay.getText().toString();
                if(!tmp.contains(".")){
                    numberHandle(".");
                }
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operatorHandle("+");
            }
        });
        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operatorHandle("-");
            }
        });
        btnMultiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operatorHandle("*");
            }
        });
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operatorHandle("/");
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy tối đa 8 kí tự đang hiển thị trên màn hình để thêm vào cuối chuỗi phép tính
                String getText = txtDisplay.getText().toString();
                for (int i = 0; i < getText.length(); i++){
                    if (i == 9) break;
                    calString += getText.charAt(i);
                }

                txtDisplay.setText(cal.processInput(calString));
                calString = cal.processInput(calString);
                Log.d("Calculator", calString);
                calString = "";
                newInput = true;
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtDisplay.setText("");
                calString = "";
                Log.d("Calculator", calString);
            }
        });
    }

    private void getComponent(){
        btnZero =  findViewById(R.id.btnZero);
        btnOne =  findViewById(R.id.btnOne);
        btnTwo =  findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);
        btnSix = findViewById(R.id.btnSix);
        btnSeven = findViewById(R.id.btnSeven);
        btnEight =  findViewById(R.id.btnEight);
        btnNine =  findViewById(R.id.btnNine);

        btnClear =  findViewById(R.id.btnClear);
        btnComma =  findViewById(R.id.btnComma);
        btnEqual =  findViewById(R.id.btnEqual);
        btnPlus = findViewById(R.id.btnPlus);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnMultiple = findViewById(R.id.btnMultiple);
        btnDivide = findViewById(R.id.btnDivide);

        txtDisplay = findViewById(R.id.txtDisplay);
    }

    // Hàm ghi nhận dấu của phép tính truyền vào
    private void operatorHandle(String operator) {
        // Nếu đang trong trạng thái nhập hay newInput = false thì nhấn các phím
        // phép tính sẽ không xảy ra điều gì cả
        if (!newInput) {
            // Lấy tối đa 9 ký tự đang hiển thị trên màn hình
            String getText = txtDisplay.getText().toString();
            for (int i =0; i< getText.length(); i++){
                if (i == 9) break;
                calString += getText.charAt(i);
            }

            String result;
            // Kiểm tra trong chuỗi có dấu phép tính hay không
            if (containsOperator(calString)) {
                // Nếu đang trong trạng thái nhập mới thì tiếng hành cộng thêm số 0 đằng
                // sau để tránh bug, tránh trường hợp người dùng spam các nút phép tính
                if (newInput) {
                    result = cal.processInput(calString + "0");
                } else {
                    result = cal.processInput(calString);
                }
            } else {
                result = cal.processInput(calString);
            }

            txtDisplay.setText(result);
            calString = result;
            // Nếu trong chuỗi phép tính không chứa dấu phép tính thì tiến hành thêm dấu phép tính vào cuối chuỗi
            if(!containsOperator(calString)){
                calString += " " + operator + " ";
            }
            // Sau khi bấm dấu phép tính thì sẽ thiết lập chế độ nhập giá trị mới
            newInput = true;
        }
        Log.d("Calculator", calString);
    }

    // Hàm xử số các giá trị số trên màn hình
    private void numberHandle(String number) {
        String displayValue = txtDisplay.getText().toString();
        // Nếu trong trạng thái nhập mới thì sẽ reset lại chuỗi đang hiển thị trên màn hình
        // và thiết lập chế độ nhập giá trị mới bằng false
        if(newInput){
            txtDisplay.setText("");
            displayValue = number;
            newInput = false;
        }else{
            displayValue += number;
        }
        txtDisplay.setText(displayValue);
        Log.d("Calculator", calString);
    }

    // Hàm kiểm tra dấu phép tính có tồn tại trong chuỗi hay không
    private Boolean containsOperator(String string) {
        return (string.contains("+") || calString.contains("-") || calString.contains("*") || calString.contains("/"));
    }
}