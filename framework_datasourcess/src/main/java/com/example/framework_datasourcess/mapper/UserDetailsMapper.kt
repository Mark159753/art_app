package com.example.framework_datasourcess.mapper

import com.example.core.model.UserProfileModel
import com.example.framework_datasourcess.model.user.UserDetails

class UserDetailsMapper:IMapper<UserDetails, UserProfileModel> {

    override fun map(from: UserDetails): UserProfileModel {
        return UserProfileModel(
            from.authenticationToken,
            from.createdAt,
            from.email,
            from.gender,
            from.id,
            from.name,
            from.paddleNumber,
            from.phone,
            from.pin,
            from.receiveSms,
            from.resetPasswordToken,
            from.timezone,
            from.timezoneCode,
            from.type,
            from.updatedAt
        )
    }
}