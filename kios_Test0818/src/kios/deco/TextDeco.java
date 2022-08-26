package kios.deco;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;

public class TextDeco {

	Font font = new FontUIResource("맑은고딕",Font.TYPE1_FONT , 12);
	public TextDeco() {
		
		
	}
	public void decoLable(JLabel lb1) {
		lb1.setFont(font);
	}
	public  void decobtn(JButton btn1) {
		btn1.setFont(font);
	}

	public  void TextFieldDeco(JTextField jt1) {
		jt1.setFont(font);
	}
}
