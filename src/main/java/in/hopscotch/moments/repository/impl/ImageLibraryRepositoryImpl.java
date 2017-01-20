package in.hopscotch.moments.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import in.hopscotch.moments.db.util.JDBCAccess;
import in.hopscotch.moments.entity.ImageLibrary;
import in.hopscotch.moments.repository.ImageLibraryRepository;

@Repository
public class ImageLibraryRepositoryImpl implements ImageLibraryRepository {

    private static final String DICTIONARY_FIND_IMAGE_SQL = "select * from DICTIONARY.ImageLibrary where image_id = ?";
    private static final String DICTIONARY_FIND_LIST_OF_IMAGE_SQL = "select * from DICTIONARY.ImageLibrary where image_id IN ($IMAGEIDS$)";
    private static final String HSMOMENTS_FIND_IMAGE_SQL = "select * from hsmoments.ImageLibrary where image_id = ?";
    private static final String HSMOMENTS_FIND_LIST_OF_IMAGE_SQL = "select * from hsmoments.ImageLibrary where image_id IN ($IMAGEIDS$)";

    @Inject
    private JDBCAccess writeJdbcAccess;

    public ImageLibrary getById(boolean isFromHSMoments, String imageId) {

        List<ImageLibrary> imageLibraries = writeJdbcAccess.find(isFromHSMoments ? HSMOMENTS_FIND_IMAGE_SQL : DICTIONARY_FIND_IMAGE_SQL, new RowMapper<ImageLibrary>() {
            @Override
            public ImageLibrary mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                return packageImageLibrary(resultSet);
            }
        }, imageId);

        if (imageLibraries == null || imageLibraries.isEmpty())
            return null;
        return imageLibraries.get(0);
    }

    public Map<String, ImageLibrary> getImageLibraryMap(boolean isFromHSMoments, Object[] params) {
        final Map<String, ImageLibrary> map = new HashMap<>();
        StringBuilder questionMark = new StringBuilder("");
        for (int i = 1; i <= params.length; i++) {

            questionMark.append('?');
            if (i != params.length)
                questionMark.append(", ");
        }
        String sqlQuery = (isFromHSMoments ? HSMOMENTS_FIND_LIST_OF_IMAGE_SQL : DICTIONARY_FIND_LIST_OF_IMAGE_SQL).replace("$IMAGEIDS$", questionMark);
        writeJdbcAccess.find(sqlQuery, new RowMapper<ImageLibrary>() {
            @Override
            public ImageLibrary mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                ImageLibrary image = packageImageLibrary(resultSet);
                map.put(image.getImageId(), image);
                return null;
            }
        }, params);
        return map;
    }

    private ImageLibrary packageImageLibrary(ResultSet resultSet) throws SQLException {
        ImageLibrary imageLibrary = new ImageLibrary();
        imageLibrary.setImageId(resultSet.getString("image_id"));
        imageLibrary.setModelName(resultSet.getString("model_name"));
        imageLibrary.setImageName(resultSet.getString("image_name"));
        imageLibrary.setImagePath(resultSet.getString("image_path"));
        imageLibrary.setFullSize(resultSet.getString("full_Size"));
        imageLibrary.setLargeSize(resultSet.getString("large_size"));
        imageLibrary.setMediumSize(resultSet.getString("medium_size"));
        imageLibrary.setThumbnailSize(resultSet.getString("thumbnail_size"));
        imageLibrary.setUploadDate(resultSet.getTimestamp("upload_date"));
        imageLibrary.setUseTimes(resultSet.getInt("use_times"));
        imageLibrary.setDescription(resultSet.getString("description"));
        imageLibrary.setVersion(resultSet.getLong("version"));
        return imageLibrary;
    }

}
