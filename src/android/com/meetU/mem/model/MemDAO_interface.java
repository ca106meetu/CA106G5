package android.com.meetU.mem.model;

import java.util.List;

public interface MemDAO_interface {
    public boolean insert(MemVO memVO);
    public void update(MemVO memVO);
    public void delete(String mem_ID);
    public MemVO findByAccount(String mem_acc);
    public MemVO findByID(String mem_ID);
    public List<MemVO> getAll();
    public boolean isMem(String mem_acc,String mem_pw);
	public boolean isAccountExist(String mem_acc);
	public byte[] getImage(String mem_acc);
	public byte[] getQRcode(String mem_ID);
    
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    //  public List<MemVO> getAll(Map<String, String[]> map); 
}
