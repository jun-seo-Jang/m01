module org.zerock.myapp {
	// To use JDK system library
	requires java.se;
	
	// To use JavaFX SDK
	
	requires javafx.controls;
	requires javafx.fxml;			//이거 3개는 꼭필요하다 의존성 설정 :)
	requires javafx.media;			//오류가 뜨면 확인후 requires 로 추가해준다 새로보이면 추가해라 :)
	
	//To use lombok
	requires lombok;							//활용하려면  module info class 파일을 확인후 추가해준다
												//modulePath에다 Add 해준다 롬복은 Add External로 해준다
	requires org.apache.logging.log4j;
	requires javafx.base;
	requires javafx.graphics;			//그리고 없으면 모듈이 아니다 ....
													//경로는 똑같이 들어가서 하면된다 (BuildPath)
													//모듈이 아닌파일을 추가하고싶으면 classpath에 추가해라 
	opens org.zerock.myapp
	to javafx.graphics, javafx.fxml;	//위에 open 시켜주면 생략해도 무방하다 !!
	
}//module
