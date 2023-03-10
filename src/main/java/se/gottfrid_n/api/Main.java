package se.gottfrid_n.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.gottfrid_n.api.objects.Version;

public class Main {
	public final String name;		public final String identifier;
	public final String version;
	public final boolean log;	public final boolean debugInfo;	private final Logger logger;

	public Main(String name, boolean log, boolean debugInfo, Version version) {
		this.identifier = name.toLowerCase();
		this.name = name;
		this.log = log;
		this.debugInfo = debugInfo;
		this.version = version.toString();
		this.logger = LoggerFactory.getLogger(this.name);
		if(!version.stable()) {
			logError(name+" "+version+" is unstable!");
		}
	}
	public void logInfo(String string) {
		if(this.log) {
			logger.info(string);
		}
	}
	public void logDebug(String string) {
		if(this.log) {
			if(!this.debugInfo) {

				return;
			}
			logInfo(string);
		}
	}
	public void logWarn(String string) {
		if(this.log) {
			logger.warn(string);
		}
	}
	public void logError(String string) {
		if(this.log) {
			logger.error(string);
		}
	}
}
