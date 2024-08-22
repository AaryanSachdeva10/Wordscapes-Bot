import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class WordscapesBot {

	private JFrame frame;
	private JLabel word;
	private JLabel lblNewLabel_1;
	private JButton btnClear, next, back;
	private JRadioButton noDup;
	private int i;
	private static JTextField first, second, third, fourth, fifth, sixth;
	private static JTextField[] fields;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WordscapesBot window = new WordscapesBot();
					window.frame.setVisible(true);
					window.frame.setTitle("Wordscapes Auto Bot");
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public WordscapesBot(){
		initialize();
	}
	private void initialize(){
		FlatMacLightLaf.setup();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		first = new JTextField();
		first.setFont(new Font("Tilt Neon", Font.PLAIN, 50));
		first.setBounds(10, 85, 50, 80);
		frame.getContentPane().add(first);
		first.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Wordscapes Auto Bot");
		lblNewLabel.setFont(new Font("Tilt Neon", Font.PLAIN, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(14, 0, 416, 69);
		frame.getContentPane().add(lblNewLabel);
		
		noDup = new JRadioButton("No Duplicates");
		noDup.setFont(new Font("Tilt Neon", Font.PLAIN, 20));
		noDup.setBounds(275, 74, 159, 21);
		noDup.setFocusable(false);
		frame.getContentPane().add(noDup);
        noDup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (noDup.isSelected()) {
                    sixth.setEnabled(true);
                }
            }
        });
		
		JRadioButton dup = new JRadioButton("Duplicate");
		dup.setFont(new Font("Tilt Neon", Font.PLAIN, 20));
		dup.setBounds(275, 106, 159, 21);
		dup.setFocusable(false);
		frame.getContentPane().add(dup);
        dup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (dup.isSelected()) {
                    sixth.setEnabled(false);
                    sixth.setText(null);
                }
            }
        });
		
		ButtonGroup group = new ButtonGroup();
		group.add(noDup);
		group.add(dup);
		noDup.setSelected(true);
		
		second = new JTextField();
		second.setFont(new Font("Tilt Neon", Font.PLAIN, 50));
		second.setColumns(10);
		second.setBounds(113, 85, 50, 80);
		frame.getContentPane().add(second);
		
		third = new JTextField();
		third.setFont(new Font("Tilt Neon", Font.PLAIN, 50));
		third.setColumns(10);
		third.setBounds(215, 85, 50, 80);
		frame.getContentPane().add(third);
		
		fourth = new JTextField();
		fourth.setFont(new Font("Tilt Neon", Font.PLAIN, 50));
		fourth.setColumns(10);
		fourth.setBounds(10, 173, 50, 80);
		frame.getContentPane().add(fourth);
		
		fifth = new JTextField();
		fifth.setFont(new Font("Tilt Neon", Font.PLAIN, 50));
		fifth.setColumns(10);
		fifth.setBounds(113, 173, 50, 80);
		frame.getContentPane().add(fifth);
		
		sixth = new JTextField();
		sixth.setFont(new Font("Tilt Neon", Font.PLAIN, 50));
		sixth.setColumns(10);
		sixth.setBounds(215, 173, 50, 80);
		frame.getContentPane().add(sixth);

		fields = new JTextField[]{first, second, third, fourth, fifth, sixth};
        ArrayList<String> words = new ArrayList<>();
		
		JButton generate = new JButton("Generate");
		generate.setFont(new Font("Tilt Neon", Font.PLAIN, 25));
		generate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        String filePath = "src\\dictionary.txt";
		        try {
		        	words.clear();
		        	next.setEnabled(true);
		            List<String> lines = Files.readAllLines(Paths.get(filePath));
		            for (String line : lines) {
		                if (generate(line, 1, 2)) {
		                    words.add(line);
		                }
		            }
		        } catch (IOException e2) {
		            e2.printStackTrace();
		            next.setEnabled(false);
		        }
		        word.setText(words.get(0));
			}
		});
		generate.setBounds(275, 164, 151, 50);
		generate.setFocusable(false);
		frame.getContentPane().add(generate);
		
		
		word = new JLabel("Word");
		word.setFont(new Font("Tilt Neon", Font.PLAIN, 56));
		word.setHorizontalAlignment(SwingConstants.CENTER);
		word.setBounds(66, 277, 300, 80);
		frame.getContentPane().add(word);
		
		lblNewLabel_1 = new JLabel("<--- DUPLICATE");
		lblNewLabel_1.setFont(new Font("Tilt Neon", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(275, 127, 184, 35);
		frame.getContentPane().add(lblNewLabel_1);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(JTextField field : fields){
					field.setText(null);
				}

				word.setText("Word");
				i = 0;
				words.clear();
				next.setEnabled(false);
				back.setEnabled(false);
				noDup.setSelected(true);
				sixth.setEnabled(true);
			}
		});
		btnClear.setFont(new Font("Tilt Neon", Font.PLAIN, 25));
		btnClear.setFocusable(false);
		btnClear.setBounds(275, 218, 151, 35);
		frame.getContentPane().add(btnClear);
		
		next = new JButton("\u2192");
		next.setEnabled(false);
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i == words.size() - 1){
					next.setEnabled(false);
				}
				else {
					i++;
					back.setEnabled(true);
					next.setEnabled(true);
					word.setText(words.get(i));
					if(i == words.size() - 1){
						next.setEnabled(false);
					}
				}
			}});
		next.setFont(new Font("Tilt Neon", Font.PLAIN, 80));
		next.setBounds(329, 277, 97, 79);
		next.setFocusable(false);
		frame.getContentPane().add(next);
		
		back = new JButton("\u2190");
		back.setEnabled(false);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i == 0){
					back.setEnabled(false);
				}
				else {
					back.setEnabled(true);
					next.setEnabled(true);
					i--;
					word.setText(words.get(i));
					
					if(i == 0){
						back.setEnabled(false);
					}
				}
			}
		});
		back.setFont(new Font("Tilt Neon", Font.PLAIN, 80));
		back.setBounds(10, 277, 97, 79);
		back.setFocusable(false);
		frame.getContentPane().add(back);
	}
   public boolean generate(String word, int usual, int special) {
        String lowercaseWord = word.toLowerCase();
        
        if (noDup.isSelected()) {
        return lowercaseWord.matches("[" + first.getText() + second.getText() + third.getText() + fourth.getText() + fifth.getText() + sixth.getText() + "-]*") &&
                lowercaseWord.chars().filter(ch -> ch == first.getText().charAt(0)).count() <= usual &&
                lowercaseWord.chars().filter(ch -> ch == second.getText().charAt(0)).count() <= usual &&
                lowercaseWord.chars().filter(ch -> ch == third.getText().charAt(0)).count() <= usual &&
                lowercaseWord.chars().filter(ch -> ch == fourth.getText().charAt(0)).count() <= usual &&
                lowercaseWord.chars().filter(ch -> ch == fifth.getText().charAt(0)).count() <= usual &&
                lowercaseWord.chars().filter(ch -> ch == sixth.getText().charAt(0)).count() <= usual;
        }
        else {
            return lowercaseWord.matches("[" + first.getText() + second.getText() + third.getText() + fourth.getText() + fifth.getText() + sixth.getText() + "-]*") &&
                    lowercaseWord.chars().filter(ch -> ch == first.getText().charAt(0)).count() <= usual &&
                    lowercaseWord.chars().filter(ch -> ch == second.getText().charAt(0)).count() <= usual &&
                    lowercaseWord.chars().filter(ch -> ch == third.getText().charAt(0)).count() <= special &&
                    lowercaseWord.chars().filter(ch -> ch == fourth.getText().charAt(0)).count() <= usual &&
                    lowercaseWord.chars().filter(ch -> ch == fifth.getText().charAt(0)).count() <= usual;
        }
    }
}