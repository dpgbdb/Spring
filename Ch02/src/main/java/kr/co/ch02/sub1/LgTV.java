package kr.co.ch02.sub1;

import org.springframework.beans.factory.annotation.Autowired;

public class LgTV {
	//어노텐션이 뭘까 오토와이어드는 뭐임
	
	@Autowired
	private Speaker spk;
	
	public void powerOn() {
		System.out.println("LgTV powerON...");
	}
	public void powerOff() {
		System.out.println("LgTV powerOff...");
	}
	public void soundUp() {
		//System.out.println("LgTV soundUp...");
		spk.soundUp();
	}
	public void soundDown() {
		//System.out.println("LgTV soundDown...");
		spk.soundUp();
	}
}
