package okhttp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.plaf.synth.SynthSpinnerUI;

public class MD5Util {

	public static void main(String[] args) throws FileNotFoundException, ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("nashorn");

		// evaluate JavaScript code
		String file = Class.class.getClassLoader().getSystemResource("./").getFile() + "md5.js";
		engine.eval(new java.io.FileReader(file));

		Object eval = engine.eval("MD5(MD5('37100631'))");
		System.out.println(eval);

		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
