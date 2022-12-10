package hu.codev.logistics.config;

import java.util.TreeMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "logistics")
@Component
public class LogisticsConfigProperties {

	private Deduction deductions = new Deduction();

	public Deduction getDeduction() {
		return deductions;
	}

	public void setDeduction(Deduction deduction) {
		this.deductions = deduction;
	}

	public static class Deduction {

		private TreeMap<Integer, Double> limits;

		public TreeMap<Integer, Double> getLimits() {
			return limits;
		}

		public void setLimits(TreeMap<Integer, Double> limits) {
			this.limits = limits;
		}

	}
	
	

}
