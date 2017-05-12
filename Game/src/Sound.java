import java.io.File;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

//调用音乐接口
class Sound{
	String path = new String("src/musics\\");
	String file = new String("nor.mid");
	Sequence seq;//字符序列Sequence 是一种数据结构，包含可由 Sequencer 对象回放的音乐信息（通常是整首歌曲或音乐作品）
	Sequencer midi;//提供用于 MIDI（音乐乐器数字接口）数据的 I/O、序列化和合成的接口和类。 
	boolean sign;
	
  void loadSound(){
	  try {
		seq = MidiSystem.getSequence(new File(path+file));
		  midi = MidiSystem.getSequencer();//将该文件读入到输入流并调用 MidiSystem 的某个 getSequence 方法
		  midi.open();
		  midi.setSequence(seq);
		  midi.start();
		  midi.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
		  
	} catch (Exception  e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  sign=true;
	
	
  }
  void mystop()
  {
	  midi.stop();
	  midi.close();
	  sign=false;
	  
  }
  boolean isplay(){
	  return sign;
  }
  void setMusic(String e){
	  file=e;
  }
	
}