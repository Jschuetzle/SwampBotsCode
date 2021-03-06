package org.firstinspires.ftc.teamcode.HardwareFunctionality;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_USING_ENCODER;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

public class Robot {

    //Hardware
    public DcMotor frontLeft = null;
    public DcMotor frontRight = null;
    public DcMotor backLeft = null;
    public DcMotor backRight = null;

    public DcMotor arm = null;

    public DcMotor carousel = null;

    public Servo leftHand = null;
    public Servo rightHand = null;

    public BNO055IMU imu = null;


    //local OpMode members
    HardwareMap hw = null;

    //Blank constructor is fine here
    public Robot(){

    }

    public void init(HardwareMap h){
        hw = h;

        //Initialize hardware
        frontRight = hw.dcMotor.get("motor1");
        frontLeft = hw.dcMotor.get("motor2");
        backRight = hw.dcMotor.get("motor3");
        backLeft = hw.dcMotor.get("motor4");
        arm = hw.dcMotor.get("motor5");
        rightHand = hw.servo.get("rightHand");
        leftHand = hw.servo.get("leftHand");
        carousel = hw.get(DcMotorEx.class, "carrouselMotor");


        //Set motor direction
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        arm.setDirection(DcMotor.Direction.REVERSE);
        rightHand.setDirection(Servo.Direction.FORWARD);
        leftHand.setDirection(Servo.Direction.REVERSE);
        carousel.setDirection(DcMotor.Direction.FORWARD);

        //Set motor power to zero
        setAllWheelPower(0);
        arm.setPower(0);
        carousel.setPower(0);

        //Set zero power behavior on motors
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        carousel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        setWheelEncoderMode(STOP_AND_RESET_ENCODER);
        setWheelEncoderMode(RUN_USING_ENCODER);
        setArmEncoderMode(STOP_AND_RESET_ENCODER);
        setArmEncoderMode(RUN_USING_ENCODER);

        leftHand.setPosition(0.74);
        rightHand.setPosition(0.41);
        
        //Make sure to initialize parameters, or IMU won't work
        imu = hw.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imu.initialize(parameters);

    }

    public void setWheelPower(double p1, double p2, double p3, double p4){
        frontLeft.setPower(p1);
        frontRight.setPower(p2);
        backLeft.setPower(p3);
        backRight.setPower(p4);
    }

    public void setAllWheelPower(double p){
        frontRight.setPower(p);
        frontLeft.setPower(p);
        backRight.setPower(p);
        backLeft.setPower(p);
    }

    public void setArmPower(double p){
        arm.setPower(p);
    }

    public void setWheelEncoderMode(DcMotor.RunMode r){
        frontLeft.setMode(r);
        frontRight.setMode(r);
        backLeft.setMode(r);
        backRight.setMode(r);
    }

    public void setArmEncoderMode(DcMotor.RunMode r){
        arm.setMode(r);
    }

    public void grabBlock(){
        leftHand.setPosition(0.85);
        rightHand.setPosition(0.54);
    }

    public void releaseBlock(){
        leftHand.setPosition(0.75);
        rightHand.setPosition(0.44);
    }


}
