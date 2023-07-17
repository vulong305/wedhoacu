package webhoacu.spring.model;

import java.io.Serializable;
import java.util.Objects;

public class ChiTietHoaDonId implements Serializable {
	
	private long maHD;
	
	private long maSP;

	
	
	public ChiTietHoaDonId() {
		
	}

	public ChiTietHoaDonId(long maHD, long maSP) {
		
		this.maHD = maHD;
		this.maSP = maSP;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maHD, maSP);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietHoaDonId other = (ChiTietHoaDonId) obj;
		return maHD == other.maHD && maSP == other.maSP;
	}
	
}
