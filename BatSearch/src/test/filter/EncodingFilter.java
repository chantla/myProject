package test.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class EncodingFilter implements Filter{
	//인코딩을 저장할 필드
	private String encoding;
	@Override
	public void destroy() {
		
		
	}

	@Override
	public void doFilter(ServletRequest request,
			ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 1. 인코딩이 설정되지 않았다면 설정해 준다.
		if(request.getCharacterEncoding()==null) {
			request.setCharacterEncoding(encoding);
		}
		// 2. 요청의 흐름 계속 이어가기
		chain.doFilter(request, response);
		
	}
	//필터가 최초 사용되는 시점에 init() 메소드가 한번만 호출된다.
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		/*	아래과 같은 web.xml 문서의 내용중에서 utf-8 문자열
		 * 	읽어내기
		 * 	<init-param>
				<param-name>encoding</param-name>
				<param-value>utf-8</param-value>
			</init-param>
			xml문서를 수정하는게 java파일수정하는것보다 좋다.
			java수정은 컴파일을 다시해야되니까. xml은 서버설정일뿐.
		 */
		//필드에 문자열 저장하기
		encoding=filterConfig.getInitParameter("encoding");
		
	}

}
