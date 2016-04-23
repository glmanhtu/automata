package file;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class FileUtils<T> extends Component {
	/**
	 * This class will decide how to export & import file
	 */
	private static final long serialVersionUID = 1L;

	public boolean generateExport(T objectToExport) throws IOException {
		String filePath = saveFileDialog();
		FileOutputStream fout = new FileOutputStream(filePath);
		ObjectOutputStream oos = new ObjectOutputStream(fout);   
		oos.writeObject(objectToExport);
		oos.close();
		return true;
	}
	
	public T readExported() throws IOException, ClassNotFoundException {
		String filePath = selectFileDialog();
		FileInputStream fin = new FileInputStream(filePath);
		ObjectInputStream ois = new ObjectInputStream(fin);
		@SuppressWarnings("unchecked")
		T result = (T) ois.readObject();
		ois.close();
		return result;
	}
	
	public String saveFileDialog() {
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			return file.getAbsolutePath();
		}
		return "";
	}
	
	public String selectFileDialog() {
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
		  	return file.getAbsolutePath();
		}
		return "";
	}
}
