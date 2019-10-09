package com.aaa.yf.action;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aaa.yf.util.ConfigUtil;
import com.aaa.yf.util.SystemUtil;

@Controller
@Action
@Scope("prototype")
public class SystemAction extends BaseAction {

	private SystemUtil system;

	public SystemUtil getSystem() {
		return system;
	}

	public void setSystem(SystemUtil system) {
		this.system = system;
	}

	/**
	 * 系统设置
	 * 
	 * @throws Exception
	 */
	public void updateSystem() throws Exception {
		try {
			// jdbc
			String jdbcPath = this.getApplication()
					.getRealPath("/install/config/jdbc.properties");

			String path = this.getApplication()
					.getRealPath("/WEB-INF/classes/cms-config.properties");
			if (system.getEncoding() != null
					&& !"".equals(system.getEncoding())) {
				ConfigUtil.writeProperties(path, "encoding",
						system.getEncoding());
			}
			if (system.getSaveDir() != null && !"".equals(system.getSaveDir())) {
				ConfigUtil
						.writeProperties(path, "saveDir", system.getSaveDir());
			}
//			if (system.getWaterMarkImg() != null
//					&& !"".equals(system.getWaterMarkImg())) {
//				ConfigUtil.writeProperties(path, "waterMarkImg",
//						system.getWaterMarkImg());
//			}
			if (system.getClick() != null && !"".equals(system.getClick())) {
				ConfigUtil.writeProperties(path, "click", system.getClick());
			}
			if (system.getClickCycle() != null
					&& !"".equals(system.getClickCycle())) {
				ConfigUtil.writeProperties(path, "clickCycle",
						system.getClickCycle());
			}
			if (system.getClickClear() != null
					&& !"".equals(system.getClickClear())) {
				ConfigUtil.writeProperties(path, "clickClear",
						system.getClickClear());
			}
			if (system.getLogClear() != null
					&& !"".equals(system.getLogClear())) {
				ConfigUtil.writeProperties(path, "logClear",
						system.getLogClear());
			}
			
			if (system.getDataBackUp() != null
					&& !"".equals(system.getDataBackUp())) {
				ConfigUtil.writeProperties(path, "dataBackUp",
						system.getDataBackUp());
			}

			if (system.getHost() != null && !"".equals(system.getHost())) {
				ConfigUtil.writeProperties(jdbcPath, "jdbc.host",
						system.getHost());
			}
			if (system.getPort() != null && !"".equals(system.getPort())) {
				ConfigUtil.writeProperties(jdbcPath, "jdbc.port",
						system.getPort());
			}
			if (system.getUserName() != null
					&& !"".equals(system.getUserName())) {
				ConfigUtil.writeProperties(jdbcPath, "jdbc.userName",
						system.getUserName());
			}
			if (system.getPassword() != null
					&& !"".equals(system.getPassword())) {
				ConfigUtil.writeProperties(jdbcPath, "jdbc.password",
						system.getPassword());
			}
			if (system.getDbName() != null && !"".equals(system.getDbName())) {
				ConfigUtil.writeProperties(jdbcPath, "jdbc.dbName",
						system.getDbName());
			}
			this.printJSON("true");
		} catch (Exception e) {
			// TODO: handle exception
			this.printJSON("false");
			this.getLogger().info("设置失败!!");
		}

	}
}
