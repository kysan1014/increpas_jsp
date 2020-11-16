package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 이 클래스는 디스패치 컨트롤러에서 요청을 처리할 때 사용할 클래스들의
 * 다형성 구현을 위하여 만들어진 인터페이스
 * 
 * @author 김의산
 * @since 2020.11.02
 * @version v.1.0
 *
 */

public interface ClsMain {

	String exec(HttpServletRequest req, HttpServletResponse resp);

	
	
}
