package com.wso2telco.workflow.dao;

import com.wso2telco.core.dbutils.DbUtils;
import com.wso2telco.core.dbutils.util.DataSourceNames;
import com.wso2telco.workflow.model.AppOperationHistoryDTO;
import com.wso2telco.workflow.utils.WorkflowServiceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalDateTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AppOperationHistoryDAO {

  private static final Log log = LogFactory.getLog(AppOperationHistoryDAO.class);

  public List<AppOperationHistoryDTO> getApplicationHistoryByApplicationId(final int applicationId) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<AppOperationHistoryDTO> historyInfoList = new ArrayList<>();

    try {
      conn = DbUtils.getDbConnection(DataSourceNames.WSO2TELCO_DEP_DB);

      if (conn == null) {
        throw new WorkflowServiceException("Unable to get DB Connection!");
      }

      final String sql = "SELECT history.ID," +
              "history.APPLICATION_ID," +
              "history.OPERATION," +
              "history.DESCRIPTION," +
              "history.PERFORMED_BY," +
              "history.PERFORMED_AT " +
              "FROM app_operation_history history " +
              "WHERE history.APPLICATION_ID = ? ORDER BY history.ID";

      ps = conn.prepareStatement(sql);
      ps.setInt(1, applicationId);
      rs = ps.executeQuery();


      while (rs.next()) {
        AppOperationHistoryDTO history = new AppOperationHistoryDTO();

        history.setId(rs.getInt("APPLICATION_ID"));
        history.setOperation(rs.getString("OPERATION"));
        history.setDescription(rs.getString("DESCRIPTION"));
        history.setPerformedBy(rs.getString("PERFORMED_BY"));
        history.setPerformedAt(LocalDateTime.fromDateFields(rs.getTimestamp("PERFORMED_AT")));

        historyInfoList.add(history);
      }
    } catch (Exception e) {
      log.error("database operation error while retriving history details : ", e);
    } finally {
      DbUtils.closeAllConnections(ps, conn, rs);
    }

    return historyInfoList;
  }
}




