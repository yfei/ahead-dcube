package cn.ahead.dcube.audit;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import cn.ahead.dcube.security.util.SecurityUtil;

/**
 * Spring audit
 * @author yangfei
 *
 */
@Configuration
public class AheadAuditorAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.ofNullable(SecurityUtil.getCurrentUser().getAccount());
	}

}
