package org.zerock.myapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class deleteControllerClass {
	
	ObservableList<String> 이욱형 = FXCollections.observableArrayList("=", ">", "<", ">=", "<=");
	
	ObservableList<String> 장준서 = FXCollections.observableArrayList("완전일치", "부분일치");
	
    @FXML
    private Label employeeIdLabel, firstNameLabel, lastNameLabel, emailLabel, phoneNumberLabel, hireDateLabel, jobIdLabel, salaryLabel, commissionPctLabel, managerIdLabel, departmentIdLabel;
    //이렇게 하자 !!
    @FXML
    private TextField employeeIdTextField;
    @FXML
    private ComboBox<String> employeeIdCondition;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private ComboBox<String> firstNameCondition;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private ComboBox<String> lastNameCondition;
    @FXML
    private TextField emailTextField;
    @FXML
    private ComboBox<String> emailCondition;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private ComboBox<String> phoneNumberCondition;
    @FXML
    private TextField hireDateTextField;
    @FXML
    private ComboBox<String> hireDateCondition;
    @FXML
    private TextField jobIdTextField;
    @FXML
    private ComboBox<String> jobIdCondition;
    @FXML
    private TextField salaryTextField;
    @FXML
    private ComboBox<String> salaryCondition;
    @FXML
    private TextField commissionPctTextField;
    @FXML
    private ComboBox<String> commissionPctCondition;
    @FXML
    private TextField managerIdTextField;
    @FXML
    private ComboBox<String> managerIdCondition;
    @FXML
    private TextField departmentIdTextField;
    @FXML
    private ComboBox<String> departmentIdCondition;

    static String driver = "oracle.jdbc.OracleDriver";
    static final String jdbcUrl2 = "jdbc:oracle:thin:@localhost:1521/XEPDB1";    // 집에서 이게 됨
//  static final String jdbcURL1 = "jdbc:oracle:thin:@XEPDB1";                    // 집에서 이게 안됨
    static String dbUser = "hr";
    static String dbPass = "oracle";
    
    @FXML
    public void initialize() {
        
        employeeIdCondition.setItems(이욱형);
        firstNameCondition.setItems(장준서);
        lastNameCondition.setItems(장준서);
        emailCondition.setItems(장준서);
        phoneNumberCondition.setItems(장준서);
        hireDateCondition.setItems(이욱형);
        jobIdCondition.setItems(장준서);
        salaryCondition.setItems(이욱형);
        commissionPctCondition.setItems(이욱형);
        managerIdCondition.setItems(이욱형);
        departmentIdCondition.setItems(이욱형);
        
        employeeIdCondition.getSelectionModel().selectFirst();
        firstNameCondition.getSelectionModel().selectFirst();
        lastNameCondition.getSelectionModel().selectFirst();
        emailCondition.getSelectionModel().selectFirst();
        phoneNumberCondition.getSelectionModel().selectFirst();
        hireDateCondition.getSelectionModel().selectFirst();
        jobIdCondition.getSelectionModel().selectFirst();
        salaryCondition.getSelectionModel().selectFirst();
        commissionPctCondition.getSelectionModel().selectFirst();
        managerIdCondition.getSelectionModel().selectFirst();
        departmentIdCondition.getSelectionModel().selectFirst();
        
    }// initialize

    @FXML
    private void handleDeleteButtonAction(ActionEvent event) {    	
    	
        String sql = "DELETE FROM employees WHERE 1=1";
        
        
        if (!employeeIdTextField.getText().isEmpty()) {
            sql += " AND employee_id " + employeeIdCondition.getValue() + " ?";
        }
        if (!firstNameTextField.getText().isEmpty()) {
            sql += " AND first_name " + (firstNameCondition.getValue().equals("완전일치") ? "=" : "LIKE") + " ?";
        }
        if (!lastNameTextField.getText().isEmpty()) {
            sql += " AND last_name " + (lastNameCondition.getValue().equals("완전일치") ? "=" : "LIKE") + " ?";
        }
        if (!emailTextField.getText().isEmpty()) {
            sql += " AND email " + (emailCondition.getValue().equals("완전일치") ? "=" : "LIKE") + " ?";
        }
        if (!phoneNumberTextField.getText().isEmpty()) {
            sql += " AND phone_number " + (phoneNumberCondition.getValue().equals("완전일치") ? "=" : "LIKE") + " ?";
        }
        if (!hireDateTextField.getText().isEmpty()) {
            sql += " AND hire_date " + hireDateCondition.getValue() + " ?";
        }
        if (!jobIdTextField.getText().isEmpty()) {
            sql += " AND job_id " + (jobIdCondition.getValue().equals("완전일치") ? "=" : "LIKE") + " ?";
        }
        if (!salaryTextField.getText().isEmpty()) {
            sql += " AND salary " + salaryCondition.getValue() + " ?";
        }
        if (!commissionPctTextField.getText().isEmpty()) {
            sql += " AND commission_pct " + commissionPctCondition.getValue() + " ?";
        }
        if (!managerIdTextField.getText().isEmpty()) {
            sql += " AND manager_id " + managerIdCondition.getValue() + " ?";
        }
        if (!departmentIdTextField.getText().isEmpty()) {
            sql += " AND department_id " + departmentIdCondition.getValue() + " ?";
        }

        log.info("SQL: {}", sql);
        
        if (sql.equals("DELETE FROM employees WHERE 1=1")) {			//조건을 아무것도 선택 안하고 삭제버튼 누를시 경고창 표시!!
        	Alert kdt = new Alert(Alert.AlertType.WARNING);
        	kdt.setHeaderText("야 똑바로해라");
        	kdt.setContentText("조건을 추가해라 이 새끼야");
        	kdt.showAndWait();
        	
        	return;
        }
        
        try {
            @Cleanup Connection conn = DriverManager.getConnection(jdbcUrl2, dbUser, dbPass);
            @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

            int index = 1;
            if (!employeeIdTextField.getText().isEmpty()) {
                pstmt.setString(index++, employeeIdTextField.getText());
            }
            if (!firstNameTextField.getText().isEmpty()) {
                pstmt.setString(index++, firstNameCondition.getValue().equals("완전일치") ? firstNameTextField.getText() : "%" + firstNameTextField.getText() + "%");
            }
            if (!lastNameTextField.getText().isEmpty()) {
                pstmt.setString(index++, lastNameCondition.getValue().equals("완전일치") ? lastNameTextField.getText() : "%" + lastNameTextField.getText() + "%");
            }
            if (!emailTextField.getText().isEmpty()) {
                pstmt.setString(index++, emailCondition.getValue().equals("완전일치") ? emailTextField.getText() : "%" + emailTextField.getText() + "%");
            }
            if (!phoneNumberTextField.getText().isEmpty()) {
                pstmt.setString(index++, phoneNumberCondition.getValue().equals("완전일치") ? phoneNumberTextField.getText() : "%" + phoneNumberTextField.getText() + "%");
            }
            if (!hireDateTextField.getText().isEmpty()) {
                pstmt.setString(index++, hireDateTextField.getText());
            }
            if (!jobIdTextField.getText().isEmpty()) {
                pstmt.setString(index++, jobIdCondition.getValue().equals("완전일치") ? jobIdTextField.getText() : "%" + jobIdTextField.getText() + "%");
            }
            if (!salaryTextField.getText().isEmpty()) {
                pstmt.setString(index++, salaryTextField.getText());
            }
            if (!commissionPctTextField.getText().isEmpty()) {
                pstmt.setString(index++, commissionPctTextField.getText());
            }
            if (!managerIdTextField.getText().isEmpty()) {
                pstmt.setString(index++, managerIdTextField.getText());
            }
            if (!departmentIdTextField.getText().isEmpty()) {
                pstmt.setString(index++, departmentIdTextField.getText());
            }    
              
            int deletedRows = pstmt.executeUpdate();
            log.info("Deleted rows: {}", deletedRows);

        } catch (SQLException e) {
            log.error("에러!!!!!!!!!", e);
        }//try-catch
    }//handleDeleteButtonAction

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        log.info("뒤로가라");
    }//handleBackButtonAction

    @FXML
    private void handleMainScreenButtonAction(ActionEvent event) {
    	log.info("홈화면으로 !!");
    }//handleMainScreenButtonAction
    
//    HR계정 INSERTINTO 하고 삭제할때 실험용 코드
//    INSERT INTO EMPLOYEES (EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
//    VALUES (300, '욱형', '이', 'ukh0701@naver.com', '010-3980-3587', to_date('2024-05-22', 'yyyy-mm-dd'), 'IT_PROG', 10000, 0.2, 100, 80);
} //end class
