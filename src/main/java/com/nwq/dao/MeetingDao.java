package com.nwq.dao;

import com.nwq.entity.Meeting;
import com.nwq.entity.Page;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @Author: nwq
 * @Description:
 * @Date: 2020/7/1 15:52
 * @Version: 1.0
 */
public class MeetingDao extends BaseDao{
    public List<Meeting> listPage(Meeting meeting, Page page) {
        String sql = "SELECT " +
                "m.* , " +
                "d.name deptName " +
                "from " +
                "meeting m " +
                "left join dept d on d.id = m.dept_id " +
                "where " +
                "m.title like ?  ";
        if (meeting.getStatus() != null) {
            sql = sql + "and m.status =" + meeting.getStatus();
        }
        sql = sql + " order by " +
                "m.publish_date DESC  " +
                "limit ?,? ";
        return template.query(sql, new BeanPropertyRowMapper<>(Meeting.class),
                "%" + meeting.getTitle() + "%",
                (page.getPageCurrent() - 1) * page.getSize(), page.getSize());
    }

    public Integer count(Meeting meeting) {
        try {
            String sql = "SELECT " +
                    "count(*) " +
                    "from " +
                    "meeting " +
                    "where " +
                    "title like ? ";
            if (meeting.getStatus() != null) {
                sql = sql + " and status =" + meeting.getStatus();
            }
            return template.queryForObject(sql, Integer.class, "%" + meeting.getTitle() + "%");
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    public Meeting getMeetingById(Integer id) {
        String sql = "SELECT " +
                "m.* , " +
                "d.name deptName " +
                "from " +
                "meeting m " +
                "left join dept d on d.id = m.dept_id " +
                "where " +
                "m.id = ?  ";
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(Meeting.class), id);
        } catch (EmptyResultDataAccessException exception) {
            // 如果是没有查询到则返回null
            return null;
        }
    }

    public void addMeeting(Meeting meeting) {
        String sql = "insert into meeting(id, dept_id,title,content, publish_date,start_time,end_time,status,make_user,create_time, create_by) values (null,?,?,?,?,?,?,?,?,?,?)";
        template.update(sql,
                meeting.getDeptId(),
                meeting.getTitle(),
                meeting.getContent(),
                meeting.getPublishDate(),
                meeting.getStartTime(),
                meeting.getEndTime(),
                meeting.getStatus(),
                meeting.getMakeUser(),
                meeting.getCreateTime(),
                meeting.getCreateBy());
    }

    public Integer getMeetingCountByMeetingId(Integer meetingId) {
        String sql = "select count(1) from meeting_join where meeting_id= ? ";
        try {
            return template.queryForObject(sql, Integer.class, meetingId);
        } catch (DataAccessException e) {
            return 0;
        }
    }

    public Integer checkJoinMeeting(Integer userId, Integer meetingId) {
        String sql = "select count(1) from meeting_join where user_id = ? and meeting_id = ? ";
        try {
            return template.queryForObject(sql, Integer.class, userId, meetingId);
        } catch (DataAccessException e) {
            return 0;
        }
    }

    public void joinMeeting(Integer userId, Integer meetingId) {
        String sql = "insert into meeting_join(meeting_id, user_id) values (?,?)";
        template.update(sql, meetingId, userId);
    }

    public void unJoinMeeting(Integer userId, Integer meetingId) {
        String sql = "delete from meeting_join where meeting_id = ? and user_id = ?";
        template.update(sql, meetingId, userId);
    }

    public List<Meeting> listAll() {
        String sql = "select * from meeting";
        return template.query(sql, new BeanPropertyRowMapper<>(Meeting.class));
    }

    public void updateStatus(Integer meetingId, Integer status) {
        String sql = "update meeting set status = ? where id = ?";
        template.update(sql, status, meetingId);
    }

}
