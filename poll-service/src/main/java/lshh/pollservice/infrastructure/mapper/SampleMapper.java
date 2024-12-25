package lshh.pollservice.infrastructure.mapper;

import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.scripting.velocity.VelocityLanguageDriver;

@Mapper
public interface SampleMapper {
    @Lang(VelocityLanguageDriver.class)
    @Select("""
            SELECT 1
            #where($_parameter.id)
                #if($velocity)
                    where id = @{id}
                #end
            #end
    """)
    int selectOne(Long id);
}
